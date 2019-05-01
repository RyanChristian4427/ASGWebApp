package com.assessment.asg.controllers.admin;

import java.util.*;

import com.assessment.asg.services.ChartService;
import com.assessment.asg.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChartController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final ChartService chartService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public ChartController(final ChartService chartService, final UserDetailsServiceImpl userDetailsService) {
        this.chartService = chartService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/chart")
    public ModelAndView chart() {
        LOGGER.info("Admin " + userDetailsService.getCurrentUserDetails().get().getUsername() + " has requested the charts page");
        Map<String, Object> model = new HashMap<>();

        //TODO figure out how to pass these to the charts js
        model.put("amtGroundSchool", chartService.findAmountOfGroundSchool());
        model.put("amtOpsManual", chartService.findAmountOfOperationsManual());
        model.put("amtFlightAssessment", chartService.findAmountOfFlightAssessment());

        model.put("amtGroundSchoolList", Collections.singletonList(chartService.findAmountOfGroundSchool()));
        model.put("amtOpsManualList", Collections.singletonList(chartService.findAmountOfOperationsManual()));
        model.put("amtFlightAssessmentList", Collections.singletonList(chartService.findAmountOfFlightAssessment()));

        return new ModelAndView("dashboard/admin/charts", model);
    }
}

