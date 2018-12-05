package com.assessment.drones.services.implementations;

import com.assessment.drones.domain.AuthenticationToken;
import com.assessment.drones.domain.User;
import com.assessment.drones.repository.interfaces.UserRepository;
import com.assessment.drones.services.interfaces.EmailService;
import com.assessment.drones.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public User emailInUse(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void createAuthenticationToken(User user, String purpose){
        //How long, from now, until the token is expired
        int tokenExpiryTimeInMinutes = 24*60;

        String token = UUID.randomUUID().toString();
        userRepository.createAuthenticationToken(new AuthenticationToken(user.getEmailAddress(), token,
                LocalDateTime.now().plusMinutes(tokenExpiryTimeInMinutes)));

        if(purpose.equalsIgnoreCase("register")) {
            String recipientAddress = user.getEmailAddress();
            String subject = "Registration Confirmation";
            String message = "Please follow this link to activate your account: " +
                    "http://localhost:8080/registrationConfirm?token=" + token;

            emailService.sendSimpleMessage(recipientAddress, subject, message);
        } else if (purpose.equalsIgnoreCase("password reset")){
            String recipientAddress = user.getEmailAddress();
            String subject = "Password Reset";
            String message = "Please follow this link to activate your account: " +
                    "http://localhost:8080/passwordReset?token=" + token;
            emailService.sendSimpleMessage(recipientAddress, subject, message);
        }
    }

    @Override
    public String authenticateUser(String token, String purpose) {

        AuthenticationToken authenticationToken = userRepository.getAuthenticationToken(token);
        if(purpose.equalsIgnoreCase("register")) {
            if (authenticationToken == null) {
                return "Sorry, but that token seems to be invalid. Make " +
                        "sure it's correct, or sign up for an account with us if you don't already have one.";
            }

            if (LocalDateTime.now().isAfter(authenticationToken.getExpiryDate())) {
                return "Sorry, but that token has expired. Please click " +
                        "below to request a new one. All tokens do expire 24 hours after they are sent out.";
            }

            userRepository.authenticateUser(authenticationToken.getUserEmail());
        } else if (purpose.equalsIgnoreCase("password reset")) {
            if (authenticationToken == null) {
                return "Sorry, but that token seems to be invalid. Make " +
                        "sure it's correct, or request a new reset token.";
            }

            if (LocalDateTime.now().isAfter(authenticationToken.getExpiryDate())) {
                return "Sorry, but that token has expired. Please click " +
                        "below to request a new one. All tokens do expire 24 hours after they are sent out.";
            }

            User user = emailInUse(authenticationToken.getUserEmail());
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user, null, Collections.singletonList(
                    new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        return null;
    }

}
