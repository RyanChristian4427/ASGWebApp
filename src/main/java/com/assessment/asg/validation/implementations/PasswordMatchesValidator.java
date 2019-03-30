package com.assessment.asg.validation.implementations;

import com.assessment.asg.models.registration.UserRegistrationDto;
import com.assessment.asg.validation.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        UserRegistrationDto user = (UserRegistrationDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
