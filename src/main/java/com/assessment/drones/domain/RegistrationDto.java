package com.assessment.drones.domain;

import com.assessment.drones.validation.annotations.PasswordMatches;
import com.assessment.drones.validation.annotations.ValidDoB;
import com.assessment.drones.validation.annotations.ValidEmail;
import com.assessment.drones.validation.annotations.ValidPostCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class RegistrationDto {

    private String firstName;
    private String lastName;

    private String emailAddress;

    private String password;
    private String matchingPassword;

    private String addressLine1;
    private String addressLine2;
    private String postCode;
    private String city;

    private String phoneNumber;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;
    private String pob;

    private String flightExperience;
    private String companyName;
    private String preferredLocation;

    private String droneMake;
    private String droneModel;

    private Long englishLevel;
    private String disability;
}
