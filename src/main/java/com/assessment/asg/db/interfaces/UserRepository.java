package com.assessment.asg.db.interfaces;

import com.assessment.asg.models.PasswordResetDto;
import com.assessment.asg.models.User;
import com.assessment.asg.models.AuthenticationToken;
import com.assessment.asg.models.registration.UserRegistrationDto;
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
