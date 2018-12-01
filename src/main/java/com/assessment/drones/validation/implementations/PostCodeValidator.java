package com.assessment.drones.validation.implementations;

import com.assessment.drones.validation.annotations.ValidPostCode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PostCodeValidator implements ConstraintValidator<ValidPostCode, String> {

    private static final Pattern postCodePattern =
            Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void initialize(ValidPostCode constraintAnnotation) {
    }
    @Override
    public boolean isValid(String postCode, ConstraintValidatorContext context){
        return postCodePattern.matcher(postCode).matches();
    }
}
