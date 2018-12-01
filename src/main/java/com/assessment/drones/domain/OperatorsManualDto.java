package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorsManualDto {
    private Long id;
    private Long candidate_number;
    private Long instructor_id;
    private String submitted_date;
    private String pass_date;
}
