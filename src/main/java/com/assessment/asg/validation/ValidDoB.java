package com.assessment.asg.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.Period;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = DateOfBirthValidator.class)
@Documented
public @interface ValidDoB {
    String message() default "Sorry, but you must be over 18.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class DateOfBirthValidator implements ConstraintValidator<ValidDoB, LocalDate> {

    @Override
    public void initialize(final ValidDoB constraintAnnotation) {}

    @Override
    public boolean isValid(final LocalDate dob, final ConstraintValidatorContext context) {
        var ageInYears = Period.between(dob, LocalDate.now()).getYears();
        return ageInYears >= 18 && ageInYears <= 100;
    }
}