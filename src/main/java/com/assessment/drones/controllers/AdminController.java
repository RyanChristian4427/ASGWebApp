package com.assessment.drones.controllers;

import com.assessment.drones.domain.*;
import com.assessment.drones.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public String getFlyTraining (@RequestParam("candidate_number") Long candidateNum,
                                  @RequestParam("training_type") String type,
                                  @RequestParam("instructor_id") Long instructorId,
                                  @RequestParam("skills_date") Date skillsDate){
        FlyTraining train = new FlyTraining();
        train.setCandidate_number(candidateNum);
        train.setTraining_type(type);
        train.setInstructor_id(instructorId);
        train.setSkills_date(skillsDate);

        adminService.addFlyTraining(train);

        return "admin";
    }

    @RequestMapping(path = "/admin/groundSchool", method = RequestMethod.GET)
    public String viewGroundSchool() {
        return "groundSchoolForm";
    }

    @RequestMapping(path = "/admin/groundSchool",  method = RequestMethod.POST)
    public String getGroundSchool(@RequestParam("candidate_number") Long cNum,
                                  @RequestParam("instructor_id") Long iNum,
                                  @RequestParam("completion_date") Date complDate,
                                  @RequestParam("question_bank") Long qBank,
                                  @RequestParam("pass_date") Date pDate,
                                  @RequestParam("pass_result") Long pResult,
                                  @RequestParam("resit") String resit){
        if(resit.equals("Y") || resit.equals("y")) {
            resit = "1";
        } else {
            resit = "0";
        }
        GroundSchool gSchool = new GroundSchool();
        gSchool.setCandidate_number(cNum);
        gSchool.setInstructor_id(iNum);
        gSchool.setCompletion_date(complDate);
        gSchool.setQuestion_bank(qBank);
        gSchool.setPass_date(pDate);
        gSchool.setPass_result(pResult);
        gSchool.setResit(Long.parseLong(resit));

        adminService.addGroundSchool(gSchool);

        return "admin";
    }

    @RequestMapping(path = "/admin/operatorsManual", method = RequestMethod.GET)
    public String viewOperatorsManual() {
        return "operatorsManualForm";
    }

    @RequestMapping(path = "/admin/operatorsManual", method = RequestMethod.POST)
    public String addOperatorsManual(@RequestParam("candidate_number") Long cNum,
                                     @RequestParam("instructor_id") Long iNum,
                                     @RequestParam("submitted_date") Date subDate,
                                     @RequestParam("pass_date") Date pDate) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        if (subDate == null){
            String temp = "0000/00/00";
            subDate = format.parse(temp);
        }
        if (pDate == null){
            String temp = "0000/00/00";
            pDate = format.parse(temp);
        }
        OperatorsManual oManual = new OperatorsManual();
        oManual.setCandidate_number(cNum);
        oManual.setInstructor_id(iNum);
        oManual.setSubmitted_date(subDate);
        oManual.setPass_date(pDate);

        adminService.addOperatorsManual(oManual);
        return "admin";
    }

    @RequestMapping(path = "/admin/flightAssessment", method = RequestMethod.GET)
    public String viewFlightAssessment() {
        return "flightAssessmentForm";
    }

    @RequestMapping(path = "/admin/flightAssessment", method = RequestMethod.POST)
    public String addFlightAssessment(){

        FlightAssessment fAssessment = new FlightAssessment();

        adminService.addFlightAssessment(fAssessment);
        return "admin";
    }

    @RequestMapping(path = "/admin/recommendations", method = RequestMethod.GET)
    public String viewRecommendations() {
        return "recommendationsForm";
    }

    @RequestMapping(path = "/admin/recommendations", method = RequestMethod.POST)
    public String addRecommendations(){

        Recommendations recommendations = new Recommendations();

        adminService.addRecommendations(recommendations);
        return "admin";
    }
}
