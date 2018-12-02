package com.assessment.drones.services.implementations;

import com.assessment.drones.repository.interfaces.UserRepository;
import com.assessment.drones.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean emailAlreadyInUse(String email) {
        return userRepository.findUserByEmail(email) == null;
    }
}
