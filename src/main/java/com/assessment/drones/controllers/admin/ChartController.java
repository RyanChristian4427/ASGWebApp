package com.assessment.drones.controllers.admin;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChartController {

    @RequestMapping(value = "/chart", method=RequestMethod.GET)
    public String chart(Model model) {
        
        //first, add the regional sales
        Integer northeastSales = 17;
        Integer midwestSales = 22;
        Integer southSales = 15;
        
        model.addAttribute("northeastSales", northeastSales);
        model.addAttribute("southSales", southSales);
        model.addAttribute("midwestSales", midwestSales);
        
        //now add sales by lure type
        List<Integer> inshoreSales = Arrays.asList(40, 34, 41);
        List<Integer> nearshoreSales = Arrays.asList(32, 30, 37);
        List<Integer> offshoreSales = Arrays.asList(78, 70, 64);
        
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
