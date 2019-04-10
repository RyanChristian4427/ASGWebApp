package com.assessment.asg.validation;


import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EnglishSpeakingLevelValidator.class)
@Documented
public @interface ValidEnglishSpeakingLevel {
    String message() default "Sorry, but your response does not match the field boundaries.";
    Class<?>[] groups() default {};
}

class EnglishSpeakingLevelValidator implements ConstraintValidator<ValidEnglishSpeakingLevel, Integer> {

    @Override
    public void initialize(final ValidEnglishSpeakingLevel constraintAnnotation) {}

    @Override
    public boolean isValid(final Integer speakingLevel, final ConstraintValidatorContext context) {
        return (0 < speakingLevel && speakingLevel < 7);
    }
}
