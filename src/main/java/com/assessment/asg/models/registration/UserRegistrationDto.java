package com.assessment.asg.models.registration;

import com.assessment.asg.validation.annotations.PasswordMatches;
import com.assessment.asg.validation.annotations.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserRegistrationDto {

    private String firstName;
    private String surname;

    @ValidEmail
    private String emailAddress;

    private String password;
    private String matchingPassword;
}
