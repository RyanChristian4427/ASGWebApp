package com.assessment.drones.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class DashboardController {

    //All of this is temp while waiting for the database to be created
    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public String viewDashboard(Principal principal, Model model) {
        model.addAttribute("userName", principal.getName());
        return "clientDashboard";
    }


}
