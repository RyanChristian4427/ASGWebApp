package com.assessment.drones.domain.courseProgress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightAssessmentDto {
    private String candidateNumber;
    private Long instructorId;
    private Boolean insurance;
    private Double loggedHours;
    private String suasCategory;
}
