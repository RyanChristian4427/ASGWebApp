package com.assessment.drones.domain;

import com.assessment.drones.validation.PasswordMatches;
import com.assessment.drones.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class UserDto {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String emailAddress;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
}
