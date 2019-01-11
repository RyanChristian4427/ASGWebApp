package com.assessment.asg.validation.implementations;

import com.assessment.asg.services.interfaces.UserService;
import com.assessment.asg.validation.annotations.ValidEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator
        implements ConstraintValidator<ValidEmail, String> {

    private UserService userService;
    private static final Pattern emailPattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    public EmailValidator(UserService aService) {
        userService = aService;
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return (validateEmail(email));
    }

    private boolean validateEmail(String emailAddress) {
        return userService.emailInUse(emailAddress) == null && emailPattern.matcher(emailAddress).matches();
    }
}