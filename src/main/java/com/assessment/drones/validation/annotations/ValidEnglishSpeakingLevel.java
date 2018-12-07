package com.assessment.drones.validation.annotations;

import com.assessment.drones.validation.implementations.EnglishSpeakingLevelValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
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
    String message() default "Sorry, but your response does not match the field.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}