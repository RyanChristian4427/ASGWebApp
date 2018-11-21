package com.assessment.drones.controllers;

import com.assessment.drones.domain.LoginDTO;
import com.assessment.drones.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class DashboardController {

    @RequestMapping(path = "/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/login",  method= RequestMethod.POST)
    public String login(LoginDTO loginDTO) {
        //potential login service, checking details and such

        //Totally temp, as this is not at all secure. Will use authentication handlers later
        return "redirect:/dashboard?user=" + loginDTO.getEmailAddress();
    }

    //All of this is temp while waiting for the database to be created
    @RequestMapping(path = "/dashboard")
    public String viewDashboard(@RequestParam(value = "user", required = false) String userEmail, Model model) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Ryan1@gmail.com", "Ryan", "Christian",  true));
        users.add(new User("Ryan2@gmail.com", "ryan", "christian",  false));

        for (User u: users) {
            if (u.getEmailAddress().equalsIgnoreCase(userEmail)) {
                model.addAttribute("user", u);
            }
        }
        return "dashboard";
    }


}
