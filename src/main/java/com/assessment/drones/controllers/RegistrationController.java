package com.assessment.drones.controllers;

import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.services.interfaces.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private CandidateService candidateService;

    @Autowired
    public RegistrationController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @RequestMapping(path="/register", method= RequestMethod.GET)
    public String register(Model model){
        RegistrationDto accountDto = new RegistrationDto();
        model.addAttribute("user", accountDto);
        return "register";
    }

    public String register(Model model, RegistrationDto accountDto){
        model.addAttribute("user", accountDto);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUserAccount(
            @ModelAttribute("user") @Valid RegistrationDto accountDto,
            BindingResult result,
            Model model) {

        if (!result.hasErrors()) {
            candidateService.registerNewCandidate(accountDto);
        }
        if (result.hasErrors()) {
            return register(model, accountDto);
        } else {
            return "redirect:/login";
        }
    }
}
