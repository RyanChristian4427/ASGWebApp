package com.assessment.asg.validation.implementations;

import com.assessment.asg.validation.annotations.ValidDoB;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class DateOfBirthValidator implements ConstraintValidator<ValidDoB, LocalDate> {

    @Override
    public void initialize(ValidDoB constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext context){
        return Period.between(dob, LocalDate.now()).getYears() >= 18;
    }
}