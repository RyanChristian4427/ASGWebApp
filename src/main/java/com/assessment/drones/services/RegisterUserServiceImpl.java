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

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmailAddress())) {
            throw new EmailExistsException(
                    "There is an account with that email address: "
                            +  accountDto.getEmailAddress());
        }

        return new User();
    }
    private boolean emailExist(String email) {
        User user = userRepository.findUserByEmail(email);
        return user != null;
    }
}
