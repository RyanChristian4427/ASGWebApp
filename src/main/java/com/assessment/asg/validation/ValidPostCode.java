package com.assessment.asg.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PostCodeValidator.class)
@Documented
public @interface ValidPostCode {
    String message() default "Post code does not valid format. Please check over what you've submitted.";
    Class<?>[] groups() default {};
}

class PostCodeValidator implements ConstraintValidator<ValidPostCode, String> {
    private static final Pattern postCodePattern =
            Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void initialize(final ValidPostCode constraintAnnotation) {}

    @Override
    public boolean isValid(final String postCode, final ConstraintValidatorContext context) {
        return postCodePattern.matcher(postCode).matches();
    }
}