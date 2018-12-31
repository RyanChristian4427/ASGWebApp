package com.assessment.asg.controllers.accounts;

import com.assessment.asg.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailAuthenticationController {

    private UserService userService;

    @Autowired
    public EmailAuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration(@RequestParam("token") String token) {
        String errors = userService.authenticateUser(token, "register");
        if (errors != null) {
            return new ModelAndView("auth-error", "message", errors);
        }
        return new ModelAndView("redirect:/login");
    }
}
