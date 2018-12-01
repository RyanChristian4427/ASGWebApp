package com.assessment.drones.validation.annotations;

import com.assessment.drones.validation.implementations.EmailValidator;

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
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
    String message() default "Email address has an invalid format, or email already in use. " +
            "Please review the address. If it is correct, the email is already in use. Please login instead.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}