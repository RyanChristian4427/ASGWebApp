package com.assessment.asg.controllers.accounts;

import com.assessment.asg.domain.User;
import com.assessment.asg.domain.registration.UserRegistrationDto;
import com.assessment.asg.domain.OnRegistrationCompleteEvent;
import com.assessment.asg.services.interfaces.UserService;
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
        return new ModelAndView("register", "user", new UserRegistrationDto());
    }

    @PostMapping(value = "/register")
    public ModelAndView registerUserAccount(final @ModelAttribute("user") @Valid UserRegistrationDto registrationDto,
                                            final BindingResult result,
                                            final WebRequest request) {
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
