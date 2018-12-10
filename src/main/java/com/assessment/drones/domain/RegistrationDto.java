package com.assessment.drones.domain;

import com.assessment.drones.validation.annotations.*;
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

    @ValidEmail
    private String emailAddress;

    private String password;
    private String matchingPassword;

    private String addressLine1;
    private String addressLine2;

    @ValidPostCode
    private String postCode;
    private String city;

    private String phoneNumber;

    @ValidEnglishSpeakingLevel
    private Integer englishLevel;
    private String disability;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    @ValidDoB
    private LocalDate dob;
    private String pob;

    private String flightExperience;
    private String companyName;
    private String preferredLocation;

    private DroneDto droneDto;
}
