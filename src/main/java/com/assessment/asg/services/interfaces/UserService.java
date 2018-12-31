package com.assessment.asg.services.interfaces;

import com.assessment.asg.domain.PasswordResetDto;
import com.assessment.asg.domain.User;
import com.assessment.asg.domain.registration.UserRegistrationDto;

public interface UserService {

    User emailInUse(String email);

    void createAuthenticationToken(User user, String purpose);

    String authenticateUser(String token, String purpose);

    void changePassword(PasswordResetDto passwordResetDto);

    User registerNewUser(UserRegistrationDto accountDto);

}
