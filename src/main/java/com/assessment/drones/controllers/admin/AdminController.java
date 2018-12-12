package com.assessment.drones.controllers.admin;

import com.assessment.drones.domain.courseProgress.*;
import com.assessment.drones.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    //method to take the user to the admin page
    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public ModelAndView viewAdmin(){
        Map<String, Object> model = new HashMap<>();
//        model.put("candidate", adminService.getCandidateList());
        model.put("flightTrainingForm", new FlightTrainingDto());
        model.put("groundSchoolForm", new GroundSchoolDto());
        model.put("operatorsManualForm", new OperatorsManualDto());
        model.put("flightAssessmentForm", new FlightAssessmentDto());
        model.put("recommendationsForm", new RecommendationsDto());

        return new ModelAndView("admin-dashboard", model);
    }

    //getting data from the flying training form
    @RequestMapping(path = "/admin/flightTraining", method = RequestMethod.POST)
    public ModelAndView getFlyTraining (@ModelAttribute("flightTrainingForm") FlightTrainingDto flightTrainingDto){
        //TODO This returns a boolean, so do an error and success page?
        adminService.verify(flightTrainingDto);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(path = "/admin/groundSchool",  method = RequestMethod.POST)
    public ModelAndView getGroundSchool(@ModelAttribute("groundSchoolForm") GroundSchoolDto groundSchoolDto){
        adminService.verify(groundSchoolDto);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(path = "/admin/operatorsManual", method = RequestMethod.POST)
    public ModelAndView addOperatorsManual(@ModelAttribute("operatorsManualForm") OperatorsManualDto operatorsManualDto){
        adminService.verify(operatorsManualDto);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(path = "/admin/flightAssessment", method = RequestMethod.POST)
    public ModelAndView addFlightAssessment(@ModelAttribute("flightAssessment") FlightAssessmentDto flightAssessmentDto){
        adminService.verify(flightAssessmentDto);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(path = "/admin/recommendations", method = RequestMethod.POST)
    public ModelAndView addRecommendations(@ModelAttribute("flightAssessment") RecommendationsDto recommendationsDto){
        adminService.verify(recommendationsDto);
        return new ModelAndView("redirect:/admin");
    }
}
