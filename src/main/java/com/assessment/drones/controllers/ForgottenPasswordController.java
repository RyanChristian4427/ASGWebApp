package com.assessment.drones.controllers;

import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.domain.User;
import com.assessment.drones.services.interfaces.EmailService;
import com.assessment.drones.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Controller
public class ForgottenPasswordController {

    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public ForgottenPasswordController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @RequestMapping(path = "/forgottenPassword", method = RequestMethod.GET)
    public String forgottenPassword(){
        return "forgottenPassword";
    }

    @RequestMapping(path = "/forgottenPassword-Error", method = RequestMethod.GET)
    public String forgottenPasswordError(ModelMap model) {
        return "forgottenPassword";
    }

    @RequestMapping(path = "/forgottenPassword", method = RequestMethod.POST)
    public String passwordReset(@ModelAttribute("emailAddress") String email, BindingResult result, ModelMap modelMap){
        if(userService.emailInUse(email)) {
//            userService.createAuthenticationToken();
        } else {
            result.rejectValue("emailAddress", "No account found with that email.");
            final String errorMessage = "No account found with that email.";
            modelMap.addAttribute("errorMessage", errorMessage);
            return "forgottenPassword";
        }
        System.out.println("Don't get here");
        return "forgottenPassword";
    }

//    @RequestMapping(value = "/user/resetPassword",
//            method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResponse resetPassword(HttpServletRequest request,
//                                         @RequestParam("email") String userEmail) {
//        User user = userService.findUserByEmail(userEmail);
//        if (user == null) {
//            throw new UserNotFoundException();
//        }
//        String token = UUID.randomUUID().toString();
//        userService.createPasswordResetTokenForUser(user, token);
//        mailSender.send(constructResetTokenEmail(getAppUrl(request),
//                request.getLocale(), token, user));
//        return new GenericResponse(
//                messages.getMessage("message.resetPasswordEmail", null,
//                        request.getLocale()));
//    }
}
