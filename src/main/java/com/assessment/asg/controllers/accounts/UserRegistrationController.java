package com.assessment.asg.controllers.accounts;

import com.assessment.asg.models.User;
import com.assessment.asg.models.registration.UserRegistrationDto;
import com.assessment.asg.models.OnRegistrationCompleteEvent;
import com.assessment.asg.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private ApplicationEventPublisher applicationEventPublisher;
    private UserService userService;

    @Autowired
    public UserRegistrationController(final ApplicationEventPublisher applicationEventPublisher,
                                      final UserService userService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.userService = userService;
    }

    @GetMapping(path = "/register")
    public ModelAndView register() {
        LOGGER.info("A user has requested the registration page.");
        return new ModelAndView("register", "user", new UserRegistrationDto());
    }

    @PostMapping(value = "/register")
    public ModelAndView registerUserAccount(final @ModelAttribute("user") @Valid UserRegistrationDto registrationDto,
                                            final BindingResult result, final WebRequest request) {
        LOGGER.info("A user with the email " + registrationDto.getEmailAddress() + " has submitted registration information.");
        if (!result.hasErrors()) {
            User user = userService.registerNewUser(registrationDto);
            String appUrl = request.getContextPath();
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
            return new ModelAndView("redirect:/login");
        } else {
            return new ModelAndView("register", "user", registrationDto);
        }
    }
}
