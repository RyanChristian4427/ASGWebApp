package com.assessment.asg.validation.implementations;

import com.assessment.asg.validation.annotations.ValidPostCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PostCodeValidator implements ConstraintValidator<ValidPostCode, String> {

    private static final Pattern POSTCODEPATTERN =
            Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void initialize(final ValidPostCode constraintAnnotation) {
    }
    @Override
    public boolean isValid(final String postCode, final ConstraintValidatorContext context) {
        return POSTCODEPATTERN.matcher(postCode).matches();
    }
}
