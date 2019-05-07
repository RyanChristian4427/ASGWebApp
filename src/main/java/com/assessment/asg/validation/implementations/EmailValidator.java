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
    private static final Pattern EMAILPATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    public EmailValidator(final UserService aService) {
        userService = aService;
    }

    @Override
    public void initialize(final ValidEmail constraintAnnotation) {
    }
    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        return (validateEmail(email));
    }

    private boolean validateEmail(final String emailAddress) {
        return !userService.isEmailInUse(emailAddress)&& EMAILPATTERN.matcher(emailAddress).matches();
    }
}
