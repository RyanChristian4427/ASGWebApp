package com.assessment.asg.controllers.admin;

import com.assessment.asg.domain.courseProgress.*;
import com.assessment.asg.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }


    //method to take the user to the admin page
    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public ModelAndView viewAdmin() {
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
        //TODO This returns a boolean, so do an error and success page?
        adminService.verify(flightTrainingDto);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(path = "/admin/groundSchool")
    public ModelAndView getGroundSchool(final @ModelAttribute("groundSchoolForm") GroundSchoolDto groundSchoolDto) {
        adminService.verify(groundSchoolDto);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(path = "/admin/operatorsManual")
    public ModelAndView addOperatorsManual(final @ModelAttribute("operatorsManualForm") OperatorsManualDto operatorsManualDto) {
        adminService.verify(operatorsManualDto);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(path = "/admin/flightAssessment")
    public ModelAndView addFlightAssessment(final @ModelAttribute("flightAssessment") FlightAssessmentDto flightAssessmentDto) {
        adminService.verify(flightAssessmentDto);
        return new ModelAndView("redirect:/admin");
    }

    @PostMapping(path = "/admin/recommendations")
    public ModelAndView addRecommendations(final @ModelAttribute("flightAssessment") RecommendationsDto recommendationsDto) {
        adminService.verify(recommendationsDto);
        return new ModelAndView("redirect:/admin");
    }
}
