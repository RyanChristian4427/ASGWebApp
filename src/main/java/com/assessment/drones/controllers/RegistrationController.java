package com.assessment.drones.controllers;

import com.assessment.drones.domain.RegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

    @RequestMapping(path="/registration", method= RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("registration", new RegistrationDTO());
        return "register";
    }
}
