package com.assessment.drones.controllers;

import com.assessment.drones.domain.PasswordResetDto;
import com.assessment.drones.domain.User;
import com.assessment.drones.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgottenPasswordController {

    private final UserService userService;

    @Autowired
    public ForgottenPasswordController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/forgottenPassword", method = RequestMethod.GET)
    public String forgottenPassword(Model model){
        model.addAttribute("passwordResetDto", new PasswordResetDto());
        return "forgotten-password";
    }

    @RequestMapping(path = "/forgottenPassword", method = RequestMethod.POST)
    public ModelAndView passwordReset(@ModelAttribute("passwordResetDto") PasswordResetDto passwordResetDto, BindingResult result){
        User user = userService.emailInUse(passwordResetDto.getEmailAddress());
        if(user != null) {
            userService.createAuthenticationToken(user, "Password reset");
        } else {
            result.rejectValue("emailAddress", "error.user","No account found with that email.");
            ModelAndView modelAndView = new ModelAndView("forgotten-password");
            return modelAndView.addObject(result);
        }
        return new ModelAndView("forgotten-password");
    }

    @RequestMapping(value = "/passwordReset", method = RequestMethod.GET)
    public String passwordReset(@RequestParam("token") String token, Model model) {

        String errors = userService.authenticateUser(token, "password reset");
        if (errors != null) {

        }

        return "redirect:/updatePassword";
    }

    @RequestMapping(path = "/updatePassword", method = RequestMethod.GET)
    public String updatePassword(Model model){
        model.addAttribute("passwordResetDto", new PasswordResetDto());
        return "update-password";
    }

    @RequestMapping(path = "/updatePassword", method = RequestMethod.POST)
    public ModelAndView updatePassword(@ModelAttribute("passwordResetDto") PasswordResetDto passwordResetDto, BindingResult result){
        User user = (User) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();
        passwordResetDto.setEmailAddress(user.getEmailAddress());
        userService.changePassword(passwordResetDto);
        return new ModelAndView("login");
    }

}
