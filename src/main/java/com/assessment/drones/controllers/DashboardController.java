package com.assessment.drones.controllers;

import com.assessment.drones.domain.LoginDto;
import com.assessment.drones.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class DashboardController {

    //All of this is temp while waiting for the database to be created
    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public String viewDashboard(@RequestParam(value = "user", required = false) String userEmail, Model model) {
        System.out.println("Login is requesting the dashboard");
        return "dashboard";
    }


}
