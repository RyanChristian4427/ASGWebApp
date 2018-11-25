package com.assessment.drones.validation;

import com.assessment.drones.domain.RegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        RegistrationDto user = (RegistrationDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}