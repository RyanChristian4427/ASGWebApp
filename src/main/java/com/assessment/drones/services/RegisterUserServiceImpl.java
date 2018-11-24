package com.assessment.drones.services;

import com.assessment.drones.controllers.exceptions.EmailExistsException;
import com.assessment.drones.domain.User;
import com.assessment.drones.domain.UserDto;
import com.assessment.drones.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private UserRepository userRepository;

    @Autowired
    public RegisterUserServiceImpl(UserRepository aUserRepository) {
        userRepository = aUserRepository;
    }

    @Override
    public User registerNewUserAccount(UserDto accountDto) {

        Integer insertResponse = userRepository.saveUser(accountDto);

        if (insertResponse == 1) {
            return new User(accountDto.getEmailAddress(), accountDto.getPassword(), "USER");
        } else {
            return null;
        }
    }

    @Override
    public boolean emailAlreadyInUse(String email) {
//        User user = userRepository.findUserByEmail(email);
        return userRepository.findUserByEmail(email) == null;
    }
}
