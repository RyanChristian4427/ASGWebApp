package com.assessment.drones.services;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository aUserRepository) {
        userRepository = aUserRepository;
    }

    @Override
    public boolean emailAlreadyInUse(String email) {
        return userRepository.findUserByEmail(email) == null;
    }
}
