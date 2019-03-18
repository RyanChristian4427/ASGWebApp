package com.assessment.asg.validation.annotations;

import com.assessment.asg.validation.implementations.PostCodeValidator;

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
@Constraint(validatedBy = PostCodeValidator.class)
@Documented
public @interface ValidPostCode {
    String message() default "Post code does not valid format. Please check over what you've submitted.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
