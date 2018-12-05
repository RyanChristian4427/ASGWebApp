package com.assessment.drones.services.implementations;

import com.assessment.drones.domain.AuthenticationToken;
import com.assessment.drones.domain.User;
import com.assessment.drones.repository.interfaces.UserRepository;
import com.assessment.drones.services.interfaces.EmailService;
import com.assessment.drones.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public boolean emailInUse(String email) {
        return userRepository.findUserByEmail(email) != null;
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
        }
    }

    @Override
    public AuthenticationToken getAuthenticationToken(String token){
        return userRepository.getAuthenticationToken(token);
    }

    @Override
    public void authenticateUser(String userEmail) {
        userRepository.authenticateUser(userEmail);
    }

}
