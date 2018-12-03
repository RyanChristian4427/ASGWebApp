package com.assessment.drones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginErrorController {

    @RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public final String displayLoginForm(Model model) {
//        model.addAttribute("loginError", true);
        return "login";
    }
}
