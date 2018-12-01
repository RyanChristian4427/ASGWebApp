package com.assessment.drones.repository;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.RegistrationDto;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository {

    User findUserByEmail(String emailAddress);
}
