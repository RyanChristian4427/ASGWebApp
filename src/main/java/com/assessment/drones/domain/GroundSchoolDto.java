package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroundSchoolDto {
    private String candidate_number;
    private Long instructor_id;
    private Date completion_date;
    private Long question_bank;
    private Long pass_result;
    private Boolean resit;
}
