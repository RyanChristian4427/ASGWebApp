package com.assessment.drones.controllers;

import com.assessment.drones.domain.*;
import com.assessment.drones.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        return "adminDashboard";
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

        return "adminDashboard";
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

        return "adminDashboard";
    }

    @RequestMapping(path = "/admin/operatorsManual", method = RequestMethod.GET)
    public String viewOperatorsManual() {
        return "operatorsManualForm";
    }

    @RequestMapping(path = "/admin/operatorsManual", method = RequestMethod.POST)
    public String addOperatorsManual(@RequestParam("candidate_number") Long cNum,
                                     @RequestParam("instructor_id") Long iNum,
                                     @RequestParam("submitted_date") String subDate,
                                     @RequestParam("pass_date") String pDate){

        OperatorsManual oManual = new OperatorsManual();
        oManual.setCandidate_number(cNum);
        oManual.setInstructor_id(iNum);
        oManual.setSubmitted_date(subDate);
        oManual.setPass_date(pDate);

        OperatorsManual om = this.adminService.findOperatorManualByInstructorAndCandidate(iNum, cNum);

        if (om != null) {
            // it exists
            om.setPass_date(pDate);
            om.setSubmitted_date(subDate);

            adminService.addOperatorsManual(om);
            adminService.save(om);

            return "adminDashboard";
        } else {
            // give an error saying not found
            throw new RuntimeException("Candidate number or instructor id not found ");
        }
    }


        @RequestMapping(path = "/admin/flightAssessment", method = RequestMethod.GET)
    public String viewFlightAssessment() {
        return "flightAssessmentForm";
    }

    @RequestMapping(path = "/admin/flightAssessment", method = RequestMethod.POST)
    public String addFlightAssessment(@RequestParam("candidate_number") Long cNum,
                                      @RequestParam("instructor_id") Long iNum,
                                      @RequestParam("insurance") String ins,
                                      @RequestParam("logged_hours") String logged,
                                      @RequestParam("suas_category") String suas,
                                      @RequestParam("assessment_pass_date") Date pass){

        FlightAssessment fAssessment = new FlightAssessment();
        fAssessment.setCandidate_number(cNum);
        fAssessment.setInstructor_id(iNum);
        fAssessment.setInsurance(ins);
        fAssessment.setLogged_hours(logged);
        fAssessment.setSuas_category(suas);
        fAssessment.setAssessment_pass_date(pass);

        FlightAssessment fa = this.adminService.findFlightAssessment(iNum,cNum);

        if (fa != null) {
            // it exists
            fa.setSuas_category(suas);
            fa.setAssessment_pass_date(pass);
            fa.setLogged_hours(logged);
            fa.setInsurance(ins);

            adminService.addFlightAssessment(fa);
            adminService.saveFlightAssessment(fa);

            return "adminDashboard";
        } else {
            // give an error saying not found
            throw new RuntimeException("Candidate number or instructor id not found ");
        }
    }

    @RequestMapping(path = "/admin/recommendations", method = RequestMethod.GET)
    public String viewRecommendations() {
        return "recommendationsForm";
    }

    @RequestMapping(path = "/admin/recommendations", method = RequestMethod.POST)
    public String addRecommendations(@RequestParam("candidate_number") Long cNum,
                                     @RequestParam("asg_recommend_date") String asg,
                                     @RequestParam("flight_competence_date") String competence,
                                     @RequestParam("application_data_date") String data_date,
                                     @RequestParam("application_date") String app_date,
                                     @RequestParam("caa_approval_date") String caa_approval,
                                     @RequestParam("overall_comments_approval_by_caa") String comments){

        Recommendations recommendations = new Recommendations();
        recommendations.setCandidate_number(cNum);
        recommendations.setAsg_recommend_date(asg);
        recommendations.setFlight_competence_date(competence);
        recommendations.setApplication_data_date(data_date);
        recommendations.setApplication_date(app_date);
        recommendations.setCaa_approval_date(caa_approval);
        recommendations.setOverall_comments_approval_by_caa(comments);

        adminService.addRecommendations(recommendations);
        return "adminDashboard";
    }
}
