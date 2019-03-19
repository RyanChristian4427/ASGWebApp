package com.assessment.asg.controllers.admin;

import java.util.*;

import com.assessment.asg.services.interfaces.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChartController {

    private final ChartService chartService;

    @Autowired
    public ChartController(final ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping(value = "/chart")
    public ModelAndView chart() {
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

