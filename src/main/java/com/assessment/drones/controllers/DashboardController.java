package com.assessment.drones.controllers;

import com.assessment.drones.domain.RegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class DashboardController {

    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public String viewDashboard(Principal principal, Model model) {
        model.addAttribute("userName", principal.getName());
        RegistrationDto accountDto = new RegistrationDto();
        model.addAttribute("user", accountDto);
        return "clientDashboard";
    }

    @RequestMapping(path = "/updateDetails", method = RequestMethod.POST)
    public void updateClientDetails() {

    }


}
