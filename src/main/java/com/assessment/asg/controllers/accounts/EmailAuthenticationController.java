package com.assessment.asg.controllers.accounts;

import com.assessment.asg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailAuthenticationController {

    private UserService userService;

    @Autowired
    public EmailAuthenticationController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/registrationConfirm")
    public ModelAndView confirmRegistration(final @RequestParam("token") String token) {
        String errors = userService.authenticateUser(token, "register");
        if (errors != null) {
            return new ModelAndView("error-generic", "message", errors);
        }
        return new ModelAndView("redirect:/login");
    }
}
