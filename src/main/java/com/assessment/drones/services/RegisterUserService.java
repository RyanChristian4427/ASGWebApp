package com.assessment.drones.services;

import com.assessment.drones.controllers.exceptions.EmailExistsException;
import com.assessment.drones.domain.User;
import com.assessment.drones.domain.UserDto;

public interface RegisterUserService {

    User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;
}
