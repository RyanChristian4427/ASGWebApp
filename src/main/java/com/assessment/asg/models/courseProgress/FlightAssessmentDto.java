package com.assessment.asg.models.courseProgress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
