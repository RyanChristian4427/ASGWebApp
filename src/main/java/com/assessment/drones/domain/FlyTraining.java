package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlyTraining {
    private Long id;
    private String candidateNum;
    private String type;
    private Long instructorId;
    private String skillsDate;
}
