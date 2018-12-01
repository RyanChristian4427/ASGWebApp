package com.assessment.drones.controllers;

import com.assessment.drones.domain.*;
import com.assessment.drones.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService aService) {
        this.adminService = aService;
    }


    //method to take the user to the admin page
    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String viewAdmin(Model model){
        model.addAttribute("flightTrainingForm", new FlightTrainingDto());
        model.addAttribute("groundSchoolForm", new GroundSchoolDto());
        model.addAttribute("operatorsManualForm", new OperatorsManualDto());
        return "adminDashboard";
    }

    //getting data from the flying training form
    @RequestMapping(path = "/admin/flightTraining", method = RequestMethod.POST)
    public String getFlyTraining (@ModelAttribute("flightTrainingForm") FlightTrainingDto flightTrainingDto){
        adminService.addFlyTraining(flightTrainingDto);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/admin/groundSchool",  method = RequestMethod.POST)
    public String getGroundSchool(@ModelAttribute("groundSchoolForm") GroundSchoolDto groundSchoolDto){
        adminService.addGroundSchool(groundSchoolDto);
        return "redirect:/admin";
    }

    @RequestMapping(path = "/admin/operatorsManual", method = RequestMethod.POST)
    public String addOperatorsManual(@ModelAttribute("operatorsManualForm") OperatorsManualDto operatorsManualDto){

        Optional<OperatorsManualDto> om = adminService.findManualByCandidate(operatorsManualDto.getCandidate_number());

        if (om.isPresent()) {
            adminService.addOperatorsManual(operatorsManualDto);

            return "redirect:/admin";
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
