package com.assessment.drones.controllers;

import com.assessment.drones.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class DashboardController {

    //All of this is temp while waiting for the database to be created
    @RequestMapping(path = "/dashboard")
    public String viewDashboard(@RequestParam(value = "user") String userName, Model model) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Ryan1", "Ryan", "Christian",  true));
        users.add(new User("Ryan2", "ryan", "christian",  false));


        for (User u: users) {
            if (u.getUserName().equals(userName)) {
                model.addAttribute("user", u);

            }
        }
        return "dashboard";
    }
}
