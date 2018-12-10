package com.assessment.drones.controllers.registration;

import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.domain.User;
import com.assessment.drones.domain.registration.UserRegistrationDto;
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
import java.util.logging.Logger;

@Controller
public class UserRegistrationController {

    private static final Logger LOGGER = Logger.getLogger(UserRegistrationController.class.getName() );
    private CandidateService candidateService;
    private ApplicationEventPublisher applicationEventPublisher;
    private UserService userService;

    @Autowired
    public UserRegistrationController(CandidateService candidateService, ApplicationEventPublisher applicationEventPublisher,
                                      UserService userService) {
        this.candidateService = candidateService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userService = userService;
    }

    @RequestMapping(path="/register", method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("register", "user", new UserRegistrationDto());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto registrationDto,
                                            BindingResult result, WebRequest request) {

        if (!result.hasErrors()) {
            User user = userService.registerNewUser(registrationDto);

            try {
                String appUrl = request.getContextPath();
                applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));

                return new ModelAndView("redirect:/login");
            } catch (Exception e) {
                LOGGER.fine("Exception with sending user registration email: " + e);
                return new ModelAndView("register","user", registrationDto);
            }
        } else {
            return new ModelAndView("register","user", registrationDto);
        }
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam("token") String token, Model model) {
        String errors = userService.authenticateUser(token, "register");
        if (errors != null) {

        }
        return "redirect:/login";
    }
}
