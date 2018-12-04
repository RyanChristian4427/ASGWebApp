package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.VerificationToken;

public interface UserService {

    boolean emailAlreadyInUse(String email);

    void createVerificationToken(VerificationToken verificationToken);

    VerificationToken getVerificationToken(String token);

    void authenticateUser(String userEmail);
}
