package com.assessment.drones.repository.interfaces;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.domain.VerificationToken;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository {

    User findUserByEmail(String emailAddress);

    void createVerificationToken(VerificationToken verificationToken);

    VerificationToken getVerificationToken(String token);

    void authenticateUser(String userEmail);

}
