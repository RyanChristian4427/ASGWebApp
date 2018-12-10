package com.assessment.drones.controllers;

import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.services.interfaces.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class DashboardController {

    private CandidateService candidateService;

    @Autowired
    public DashboardController(CandidateService candidateService){
        this.candidateService=candidateService;
    }

    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public String viewDashboard(Principal principal, Model model) {
        model.addAttribute("userName", principal.getName());

        CourseRegistrationDto accountDto = new CourseRegistrationDto();
        model.addAttribute("updateAddress", accountDto);
        return "client-dashboard";
    }

    @RequestMapping(path = "/updateDetails", method = RequestMethod.POST)
    public void updateClientDetails(@ModelAttribute("updateAddress") CourseRegistrationDto accountDto) {
        candidateService.registerNewCandidate(accountDto);
    }


}
