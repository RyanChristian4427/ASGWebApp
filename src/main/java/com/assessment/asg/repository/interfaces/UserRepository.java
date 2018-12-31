package com.assessment.asg.repository.interfaces;

import com.assessment.asg.domain.PasswordResetDto;
import com.assessment.asg.domain.User;
import com.assessment.asg.domain.AuthenticationToken;
import com.assessment.asg.domain.registration.UserRegistrationDto;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository {

    User findUserByEmail(String emailAddress);

    void createAuthenticationToken(AuthenticationToken authenticationToken);

    AuthenticationToken getAuthenticationToken(String token);

    void authenticateUser(String userEmail);

    void changePassword(PasswordResetDto passwordResetDto);

    Integer saveUser(UserRegistrationDto registrationDto);

}
