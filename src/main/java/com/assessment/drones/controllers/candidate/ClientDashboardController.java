package com.assessment.drones.controllers.candidate;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.services.interfaces.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class ClientDashboardController {

    private CandidateService candidateService;

    @Autowired
    public ClientDashboardController(CandidateService candidateService){
        this.candidateService=candidateService;
    }

    @RequestMapping(path = "/dashboard", method = RequestMethod.GET)
    public ModelAndView viewDashboard(Principal principal) {
        Optional<Candidate> candidate = candidateService.findCandidateByEmail(principal.getName());

        Map<String, Object> model = new HashMap<>();
        model.put("updateAddress", new CourseRegistrationDto());
        model.put("courseRegistration", new CourseRegistrationDto());

        if (candidate.isPresent()) {
            model.put("userRegistered", true);
        } else {
            model.put("userRegistered", false);
        }
        return new ModelAndView("client-dashboard", model);
    }

    @RequestMapping(path = "/updateDetails", method = RequestMethod.POST)
    public void updateClientDetails(@ModelAttribute("updateAddress") CourseRegistrationDto accountDto) {
        candidateService.registerNewCandidate(accountDto);
    }


}
