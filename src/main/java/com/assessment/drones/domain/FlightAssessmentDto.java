package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightAssessmentDto {
    private String candidate_number;
    private Long instructor_id;
    private String insurance;
    private Double logged_hours;
    private String suas_category;
}
