package com.assessment.drones.controllers;

import com.assessment.drones.domain.*;
import com.assessment.drones.services.AdminService;
import com.assessment.drones.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService aService) {
        adminService = aService;
    }


    //method to take the user to the admin page
    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String viewAdmin(Model model){
        Map<String, Object> attributes = new HashMap<>()
        {{
            put("flightTrainingForm", new FlightTrainingDto());
            put("groundSchoolForm", new GroundSchoolDto());
            put("operatorsManualForm", new OperatorsManualDto());
            put("flightAssessmentFromForm", new FlightAssessmentDto());
            put("recommendationsForm", new RecommendationsDto());

        }};
        model.addAllAttributes(attributes);
        return "adminDashboard";
    }

    //getting data from the flying training form
    @RequestMapping(path = "/admin/flightTraining", method = RequestMethod.POST)
    public String getFlyTraining (@ModelAttribute("flightTrainingForm") FlightTrainingDto flightTrainingDto){
        //This returns a boolean, so do an error and success page?
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
    public String addRecommendations(@RequestParam("candidate_number") Long cNum,
                                     @RequestParam("asg_recommend_date") String asg,
                                     @RequestParam("flight_competence_date") String competence,
                                     @RequestParam("application_data_date") String data_date,
                                     @RequestParam("application_date") String app_date,
                                     @RequestParam("caa_approval_date") String caa_approval,
                                     @RequestParam("overall_comments_approval_by_caa") String comments){

        RecommendationsDto recommendationsDto = new RecommendationsDto();
        recommendationsDto.setCandidate_number(cNum);
        recommendationsDto.setAsg_recommend_date(asg);
        recommendationsDto.setFlight_competence_date(competence);
        recommendationsDto.setApplication_data_date(data_date);
        recommendationsDto.setApplication_date(app_date);
        recommendationsDto.setCaa_approval_date(caa_approval);
        recommendationsDto.setOverall_comments_approval_by_caa(comments);

        adminService.addRecommendations(recommendationsDto);
        return "adminDashboard";
    }
}
