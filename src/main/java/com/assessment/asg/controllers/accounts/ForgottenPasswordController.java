package com.assessment.asg.controllers.accounts;

import com.assessment.asg.models.PasswordResetDto;
import com.assessment.asg.models.User;
import com.assessment.asg.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgottenPasswordController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    @Autowired
    public ForgottenPasswordController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/forgottenPassword")
    public String forgottenPassword(final Model model) {
        LOGGER.info("Someone has requested the password reset page");
        model.addAttribute("passwordResetDto", new PasswordResetDto());
        return "forgotten-password";
    }

    @PostMapping(path = "/forgottenPassword")
    public ModelAndView passwordReset(final @ModelAttribute("passwordResetDto") PasswordResetDto passwordResetDto,
                                      final BindingResult result) {
        LOGGER.info("Someone has requested a new password for the email " + passwordResetDto.getEmailAddress());
        User user = userService.emailInUse(passwordResetDto.getEmailAddress());
        if (user != null) {
            userService.createAuthenticationToken(user, "Password reset");
            LOGGER.info(passwordResetDto.getEmailAddress() + " is a valid user, sending authentication email.");
        } else {
            result.rejectValue("emailAddress", "error.user", "No account found with that email.");
            ModelAndView modelAndView = new ModelAndView("forgotten-password");
            LOGGER.info(passwordResetDto.getEmailAddress() + " is not a valid user, can not send authentication email.");
            return modelAndView.addObject(result);
        }
        return new ModelAndView("forgotten-password");
    }

    @GetMapping(value = "/passwordReset")
    public String passwordReset(final @RequestParam("token") String token) {
        LOGGER.info("User is requesting a new password with token " + token);
        String errors = userService.authenticateUser(token, "password reset");
//        if (errors != null) {
//
//        }
        return "redirect:/updatePassword";
    }

    @GetMapping(path = "/updatePassword")
    public String updatePassword(final Model model) {
        model.addAttribute("passwordResetDto", new PasswordResetDto());
        return "update-password";
    }

    @PostMapping(path = "/updatePassword")
    public ModelAndView updatePassword(final @ModelAttribute("passwordResetDto") PasswordResetDto passwordResetDto) {
        LOGGER.info("User has submitted a new password for their account.");
        User user = (User) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();
        passwordResetDto.setEmailAddress(user.getEmailAddress());
        userService.changePassword(passwordResetDto);
        return new ModelAndView("login");
    }

}
