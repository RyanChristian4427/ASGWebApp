package com.assessment.drones.repository.interfaces;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.AuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository {

    User findUserByEmail(String emailAddress);

    void createAuthenticationToken(AuthenticationToken authenticationToken);

    AuthenticationToken getAuthenticationToken(String token);

    void authenticateUser(String userEmail);

}
