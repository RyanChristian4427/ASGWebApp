package com.assessment.asg.validation.implementations;

import com.assessment.asg.validation.annotations.ValidDoB;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class DateOfBirthValidator implements ConstraintValidator<ValidDoB, LocalDate> {

    @Override
    public void initialize(final ValidDoB constraintAnnotation) {
    }

    @Override
    public boolean isValid(final LocalDate dob, final ConstraintValidatorContext context) {
        return Period.between(dob, LocalDate.now()).getYears() >= 18;
    }
}
