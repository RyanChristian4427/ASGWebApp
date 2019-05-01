package com.assessment.asg.controllers.admin;

import com.assessment.asg.models.courseProgress.*;
import com.assessment.asg.services.AdminService;
import com.assessment.asg.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private AdminService adminService;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public AdminController(final AdminService adminService, final UserDetailsServiceImpl userDetailsService) {
        this.adminService = adminService;
        this.userDetailsService = userDetailsService;
    }


    //method to take the user to the admin page
    @GetMapping(path = "/admin")
    public ModelAndView viewAdmin() {
        LOGGER.info("Admin " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has requested the admin dashboard");
        Map<String, Object> model = new HashMap<>();
//        model.put("candidate", adminService.getCandidateList());
        model.put("flightTrainingForm", new FlightTrainingDto());
        model.put("groundSchoolForm", new GroundSchoolDto());
        model.put("operatorsManualForm", new OperatorsManualDto());
        model.put("flightAssessmentForm", new FlightAssessmentDto());
        model.put("recommendationsForm", new RecommendationsDto());

        return new ModelAndView("dashboard/admin/index", model);
    }

    //getting data from the flying training form
    @PostMapping(path = "/admin/flightTraining")
    public ModelAndView getFlyTraining(final @ModelAttribute("flightTrainingForm") FlightTrainingDto flightTrainingDto) {
        LOGGER.info("Admin " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has submitted new flight training information.");
        //TODO This returns a boolean, so create an error and success page?
        adminService.verify(flightTrainingDto);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(path = "/admin/groundSchool")
    public ModelAndView getGroundSchool(final @ModelAttribute("groundSchoolForm") GroundSchoolDto groundSchoolDto) {
        LOGGER.info("Admin " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has submitted new ground school information.");
        adminService.verify(groundSchoolDto);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(path = "/admin/operatorsManual")
    public ModelAndView addOperatorsManual(final @ModelAttribute("operatorsManualForm") OperatorsManualDto operatorsManualDto) {
        LOGGER.info("Admin " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has submitted new operators manual information.");
        adminService.verify(operatorsManualDto);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(path = "/admin/flightAssessment")
    public ModelAndView addFlightAssessment(final @ModelAttribute("flightAssessment") FlightAssessmentDto flightAssessmentDto) {
        LOGGER.info("Admin " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has submitted new flight assessment information.");
        adminService.verify(flightAssessmentDto);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(path = "/admin/recommendations")
    public ModelAndView addRecommendations(final @ModelAttribute("flightAssessment") RecommendationsDto recommendationsDto) {
        LOGGER.info("Admin " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has submitted new recommendations information.");
        adminService.verify(recommendationsDto);
        return new ModelAndView("redirect:/admin");
    }
}
