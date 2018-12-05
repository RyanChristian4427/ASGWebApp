package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.AuthenticationToken;
import com.assessment.drones.domain.User;

public interface UserService {

    boolean emailInUse(String email);

    void createAuthenticationToken(User user, String purpose);

    AuthenticationToken getAuthenticationToken(String token);

    void authenticateUser(String userEmail);
}
