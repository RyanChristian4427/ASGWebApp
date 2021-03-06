package com.assessment.asg.domain.registration;

import com.assessment.asg.validation.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistrationDto {

    private String emailAddress;
    private String referenceNumber;

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
    private LocalDate dob;
    private String pob;

    private String flightExperience;
    private String companyName;
    private String preferredLocation;

    private DroneDto droneDto;

    private Boolean paid = false;
}
