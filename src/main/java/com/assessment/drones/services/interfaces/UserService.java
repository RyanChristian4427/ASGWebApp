package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.PasswordResetDto;
import com.assessment.drones.domain.User;
import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.domain.registration.UserRegistrationDto;

public interface UserService {

    User emailInUse(String email);

    void createAuthenticationToken(User user, String purpose);

    String authenticateUser(String token, String purpose);

    void changePassword(PasswordResetDto passwordResetDto);

    User registerNewUser(UserRegistrationDto accountDto);

}
