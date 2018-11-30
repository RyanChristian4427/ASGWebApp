package com.assessment.drones.domain;

import com.assessment.drones.validation.PasswordMatches;
import com.assessment.drones.validation.ValidDoB;
import com.assessment.drones.validation.ValidEmail;
import com.assessment.drones.validation.ValidPostCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static java.sql.JDBCType.DATE;

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
    @ValidPostCode
    private String postCode;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    private String companyName;

    @NotNull
    @ValidDoB
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

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
    private String droneMake;

    @NotNull
    @NotEmpty
    private String droneModel;
}
