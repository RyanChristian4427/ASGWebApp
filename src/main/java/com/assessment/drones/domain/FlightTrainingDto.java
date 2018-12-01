package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightTrainingDto {;
    private String candidateNumber;
    private Long instructorId;
    private String trainingType;
    private LocalDate skillsDate;
}
