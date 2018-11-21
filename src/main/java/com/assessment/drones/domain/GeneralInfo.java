package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class GeneralInfo {
    private Long id;
    private String firstName;
    private String surname;
    private String dob;
    private String placeOfBirth;
    private Long addressID;
    private String companyName;
    private String prevFlyingExp;
    private String location;
    private String droneType;
    private String candidateNumber;
}
