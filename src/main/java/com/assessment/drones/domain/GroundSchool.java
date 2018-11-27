package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroundSchool {
    private Long id;
    private String candidate_number;
    private Long instructor_id;
    private Date completion_date;
    private Long question_bank;
    private Date pass_date;
    private Long pass_result;
    private Long resit;
}
