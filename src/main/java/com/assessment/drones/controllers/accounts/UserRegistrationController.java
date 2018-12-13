package com.assessment.drones.controllers.accounts;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.registration.UserRegistrationDto;
import com.assessment.drones.domain.OnRegistrationCompleteEvent;
import com.assessment.drones.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private ApplicationEventPublisher applicationEventPublisher;
    private UserService userService;

    @Autowired
    public UserRegistrationController(ApplicationEventPublisher applicationEventPublisher, UserService userService) {
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

            String appUrl = request.getContextPath();
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
            return new ModelAndView("redirect:/login");
        } else {
            return new ModelAndView("register","user", registrationDto);
        }
    }
}
