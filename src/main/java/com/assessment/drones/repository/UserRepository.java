package com.assessment.drones.repository;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.UserDto;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository {

    User findUserByEmail(String emailAddress);

    Integer saveUser(UserDto accountDto);
}
