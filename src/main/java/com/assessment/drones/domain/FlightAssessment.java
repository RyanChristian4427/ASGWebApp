package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightAssessment {
    private Long id;
    private Long candidate_number;
    private Long instructor_id;
    private String insurance;
    private String logged_hours;
    private String suas_category;
    private Date assessment_pass_date;
}
