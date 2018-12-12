package com.assessment.drones.controllers;

import java.util.Arrays;
import java.util.List;

import com.assessment.drones.services.interfaces.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChartController {

    ChartService chartService;

    @Autowired
    public ChartController(ChartService chartService){
        this.chartService = chartService;
    }

    @RequestMapping(value = "/chart", method=RequestMethod.GET)
    public String chart(Model model) {

        //first, add the regional sales
        Integer northeastSales = chartService.findAmountOfOperationsManual();
        Integer midwestSales = chartService.findAmountOfCandidates();
        Integer southSales = chartService.findAmountOfGroundSchool();

        model.addAttribute("northeastSales", northeastSales);
        model.addAttribute("southSales", southSales);
        model.addAttribute("midwestSales", midwestSales);

        //now add sales by lure type
        List<Integer> inshoreSales = Arrays.asList(chartService.findAmountOfGroundSchool());
        List<Integer> nearshoreSales = Arrays.asList(chartService.findAmountOfOperationsManual());
        List<Integer> offshoreSales = Arrays.asList(chartService.findAmountOfCandidates());

        model.addAttribute("inshoreSales", inshoreSales);
        model.addAttribute("nearshoreSales", nearshoreSales);
        model.addAttribute("offshoreSales", offshoreSales);

        return "admin-charts";
    }


    //redirect to demo if user hits the root
    @RequestMapping("/")
    public String home(Model model) {
        return "redirect:chart";
    }
}

