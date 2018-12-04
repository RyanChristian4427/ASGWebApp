package com.assessment.drones.controllers;

import com.assessment.drones.domain.*;
import com.assessment.drones.services.interfaces.AdminService;
import com.assessment.drones.services.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    //method to take the user to the admin page
    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String viewAdmin(Model model){
        model.addAttribute("flightTrainingForm", new FlightTrainingDto());
        model.addAttribute("groundSchoolForm", new GroundSchoolDto());
        model.addAttribute("operatorsManualForm", new OperatorsManualDto());
        model.addAttribute("flightAssessmentForm", new FlightAssessmentDto());
        model.addAttribute("recommendationsForm", new RecommendationsDto());
        return "adminDashboard";
    }

    //getting data from the flying training form
    @RequestMapping(path = "/admin/flightTraining", method = RequestMethod.POST)
    public String getFlyTraining (@ModelAttribute("flightTrainingForm") FlightTrainingDto flightTrainingDto){
        //TODO This returns a boolean, so do an error and success page?
        adminService.verify(flightTrainingDto);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/admin/groundSchool",  method = RequestMethod.POST)
    public String getGroundSchool(@ModelAttribute("groundSchoolForm") GroundSchoolDto groundSchoolDto){
        adminService.verify(groundSchoolDto);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/admin/operatorsManual", method = RequestMethod.POST)
    public String addOperatorsManual(@ModelAttribute("operatorsManualForm") OperatorsManualDto operatorsManualDto){
        adminService.verify(operatorsManualDto);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/admin/flightAssessment", method = RequestMethod.POST)
    public String addFlightAssessment(@ModelAttribute("flightAssessment") FlightAssessmentDto flightAssessmentDto){
        adminService.verify(flightAssessmentDto);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/admin/recommendations", method = RequestMethod.POST)
    public String addRecommendations(@ModelAttribute("flightAssessment") RecommendationsDto recommendationsDto){
        adminService.verify(recommendationsDto);
        return "redirect:/admin";
    }
}
