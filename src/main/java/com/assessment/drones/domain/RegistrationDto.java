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
public class RegistrationDto {

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

    @NotNull
    @NotEmpty
    private String addressLine1;
    private String addressLine2;

    @NotNull
    @NotEmpty
    private String postCode;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    private String companyName;

    @NotNull
    @NotEmpty
    private String dob;

    @NotNull
    @NotEmpty
    private String pob;

    @NotNull
    @NotEmpty
    private String flightExperience;

    @NotNull
    @NotEmpty
    private String preferredLocation;

    @NotNull
    @NotEmpty
    private String droneType;
}
