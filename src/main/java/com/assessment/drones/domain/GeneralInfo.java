package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralInfo {
    private Long id;
    private String dob;
    private String placeOfBirth;
    private String companyName;
    private String prevFlyingExp;
    private Long droneTypeId;
}
