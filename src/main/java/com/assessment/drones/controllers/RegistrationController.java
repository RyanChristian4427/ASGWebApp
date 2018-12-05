package com.assessment.drones.controllers;

import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.domain.User;
import com.assessment.drones.domain.AuthenticationToken;
import com.assessment.drones.services.OnRegistrationCompleteEvent;
import com.assessment.drones.services.interfaces.CandidateService;
import com.assessment.drones.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class RegistrationController {

    private CandidateService candidateService;
    private ApplicationEventPublisher applicationEventPublisher;
    private UserService userService;

    @Autowired
    public RegistrationController(CandidateService candidateService, ApplicationEventPublisher applicationEventPublisher,
                                  UserService userService) {
        this.candidateService = candidateService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userService = userService;
    }

    @RequestMapping(path="/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new RegistrationDto());
        return "register";
    }

    public String register(Model model, RegistrationDto accountDto){
        model.addAttribute("user", accountDto);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid RegistrationDto accountDto,
                                            BindingResult result, WebRequest request) {

        if (!result.hasErrors()) {
            User user = candidateService.registerNewCandidate(accountDto);

            try {
                String appUrl = request.getContextPath();
                applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));

                return new ModelAndView("/login");
            } catch (Exception me) {
                return new ModelAndView("registration","user", accountDto);
            }
        } else {
//            ModelAndView modelAndView = new ModelAndView("/register");
//            modelAndView.addObject()
            return new ModelAndView("registration","user", accountDto);
        }
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(Model model, @RequestParam("token") String token) {

        AuthenticationToken authenticationToken = userService.getAuthenticationToken(token);
        if (authenticationToken == null) {
            model.addAttribute("message", "Sorry, but that token seems to be invalid. Make " +
                    "sure it's correct, or sign up for an account with us if you don't already have one.");
            return "authError";
        }

        if (LocalDateTime.now().isAfter(authenticationToken.getExpiryDate())) {
            model.addAttribute("message", "Sorry, but that token has expired. Please click " +
                    "below to request a new one. All tokens do expire 24 hours after they are sent out.");
            return "authError";
        }

        userService.authenticateUser(authenticationToken.getUserEmail());
        return "redirect:/login";
    }
}
