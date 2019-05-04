package com.assessment.asg.validation;

import com.assessment.asg.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
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
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
    String message() default "Email address has an invalid format, or email already in use. " +
            "Please review the address. If it is correct, the email is already in use. Please login instead.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private UserService userService;
    private static final Pattern emailPattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    public EmailValidator(final UserService aService) {
        userService = aService;
    }

    @Override
    public void initialize(final ValidEmail constraintAnnotation) {}

    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        return (validateEmail(email));
    }

    private boolean validateEmail(final String emailAddress) {
        return userService.emailInUse(emailAddress) == null && emailPattern.matcher(emailAddress).matches();
    }
}