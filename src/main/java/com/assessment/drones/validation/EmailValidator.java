package com.assessment.drones.validation;

import com.assessment.drones.services.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator
        implements ConstraintValidator<ValidEmail, String> {

    private RegisterUserService registerUserService;

    @Autowired
    public EmailValidator(RegisterUserService aService) {
        registerUserService = aService;
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return (validateEmail(email));
    }

    private boolean validateEmail(String email) {
        return registerUserService.emailAlreadyInUse(email);
    }
}