package com.assessment.asg.controllers.admin;

import java.util.*;

import com.assessment.asg.services.interfaces.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChartController {

    private final ChartService chartService;

    @Autowired
    public ChartController(ChartService chartService){
        this.chartService = chartService;
    }

    @RequestMapping(value = "/chart", method=RequestMethod.GET)
    public ModelAndView chart() {

        Map<String, Object> model = new HashMap<>();

        model.put("amtGroundSchool", chartService.findAmountOfGroundSchool());
        model.put("amtOpsManual", chartService.findAmountOfOperationsManual());
        model.put("amtFlightAssessment", chartService.findAmountOfFlightAssessment());

        model.put("amtGroundSchoolList", Collections.singletonList(chartService.findAmountOfGroundSchool()));
        model.put("amtOpsManualList", Collections.singletonList(chartService.findAmountOfOperationsManual()));
        model.put("amtFlightAssessmentList", Collections.singletonList(chartService.findAmountOfFlightAssessment()));

        return new ModelAndView("admin-charts", model);
    }
}

