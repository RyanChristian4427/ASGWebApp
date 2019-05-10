package com.assessment.asg.controllers.accounts;

import com.assessment.asg.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailAuthenticationController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private UserService userService;

    @Autowired
    public EmailAuthenticationController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/registrationConfirm")
    public ModelAndView confirmRegistration(final @RequestParam("token") String token) {
        LOGGER.info("Someone has attempted to confirm their registration.");
        String errors = userService.authenticateUser(token, "register");
        if (errors != null) {
            return new ModelAndView("errors/error-generic", "message", errors);
        }
        LOGGER.info("User using token: " + token + " has successfully registered for the site.");
        return new ModelAndView("redirect:/login");
    }
}
