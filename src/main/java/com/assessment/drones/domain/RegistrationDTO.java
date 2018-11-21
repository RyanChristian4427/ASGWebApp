package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private Long courseNumber;
    private Date startDate;
    private Date endDate;
}
