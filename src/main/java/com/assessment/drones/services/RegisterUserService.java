package com.assessment.drones.services;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.RegistrationDto;

public interface RegisterUserService {

    User registerNewUserAccount(RegistrationDto accountDto);

    boolean emailAlreadyInUse(String email);
}
