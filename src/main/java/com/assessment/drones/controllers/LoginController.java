package com.assessment.drones.controllers;

import com.assessment.drones.domain.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
//public class LoginController {
//
//    @RequestMapping(path = "/login", method= RequestMethod.GET)
//    public String login() {
//        return "login";
//    }
//
//    @RequestMapping(path = "/login",  method= RequestMethod.POST)
//    public String login(LoginDto loginDTO) {
//        //potential login service, checking details and such
//
//        //Totally temp, as this is not at all secure. Will use authentication handlers later
//        return "redirect:/dashboard";
//    }
//}
