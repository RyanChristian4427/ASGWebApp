package com.assessment.drones.controllers;

import com.assessment.drones.domain.FlyTraining;
import com.assessment.drones.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService aService) {
        this.adminService = aService;
    }


    //method to take the user to the admin page
    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String viewAdmin (){
        return "admin";
    }

    //method to take the admin to the flying training form
    @RequestMapping(path = "/admin/flyingTraining", method = RequestMethod.GET)
    public String viewFlyTraining () {
        return "flyTrainForm";
    }

    //getting data from the flying training form
    @RequestMapping(path = "/admin/flyingTraining", method = RequestMethod.POST)
    public String getFlyTraining (@RequestParam("candidate_number") String candidateNum,
                                  @RequestParam("type") String type,
                                  @RequestParam("instructor_id") Long instructorId,
                                  @RequestParam("skills_date") Date skillsDate){
        FlyTraining train = new FlyTraining();
        train.setCandidate_number(candidateNum);
        train.setType(type);
        train.setInstructor_id(instructorId);
        train.setSkills_date(skillsDate);

        adminService.addFlyTraining(train);

        return "admin";
    }

    @RequestMapping(path = "/admin/groundSchool", method = RequestMethod.GET)
    public String viewGroundSchool() {
        return "groundSchoolForm";
    }

    @RequestMapping(path = "/admin/operatorsManual", method = RequestMethod.GET)
    public String viewOperatorsManual() {
        return "operatorsManualForm";
    }

    @RequestMapping(path = "/admin/flightAssessment", method = RequestMethod.GET)
    public String viewFlightAssessment() {
        return "flightAssessmentForm";
    }

    @RequestMapping(path = "/admin/recommendations", method = RequestMethod.GET)
    public String viewRecommendations() {
        return "recommendationsForm";
    }
}
