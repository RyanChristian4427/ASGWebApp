package com.assessment.asg.services;

import com.assessment.asg.domain.OnRegistrationCompleteEvent;
import com.assessment.asg.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private UserService userService;

    @Autowired
    public RegistrationListener(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        userService.createAuthenticationToken(event.getUser(), "register");
    }
}
