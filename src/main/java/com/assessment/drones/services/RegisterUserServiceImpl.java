package com.assessment.drones.services;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private UserRepository userRepository;

    @Autowired
    public RegisterUserServiceImpl(UserRepository aUserRepository) {
        userRepository = aUserRepository;
    }

    @Override
    public User registerNewUserAccount(RegistrationDto accountDto) {

        Integer insertResponse = userRepository.saveUser(accountDto);

        if (insertResponse == 1) {
            return new User(accountDto.getEmailAddress(), accountDto.getPassword(), "USER");
        } else {
            return null;
        }
    }

    @Override
    public boolean emailAlreadyInUse(String email) {
        return userRepository.findUserByEmail(email) == null;
    }
}
