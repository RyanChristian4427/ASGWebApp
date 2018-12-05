package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.User;

public interface UserService {

    User emailInUse(String email);

    void createAuthenticationToken(User user, String purpose);

    String authenticateUser(String token, String purpose);
}
