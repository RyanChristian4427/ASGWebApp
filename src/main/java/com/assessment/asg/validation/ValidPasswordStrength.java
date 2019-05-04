package com.assessment.asg.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Documented
public @interface ValidPasswordStrength {
    String message() default "Password strength check failed. We require an upper and lower case letter, " +
            "a number, a special character (including space), and it needs to be 10 characters long. ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class PasswordStrengthValidator implements ConstraintValidator<ValidPasswordStrength, String> {

    private static final Pattern passwordPattern = Pattern.compile("^(?=.{10,128}$)(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?:([\\w\\d!@#$%^&*? :;])\\1?(?!\\1))+$");

    @Override
    public void initialize(final ValidPasswordStrength constraintAnnotation) {}

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        return (passwordPattern.matcher(password).matches());
    }
}