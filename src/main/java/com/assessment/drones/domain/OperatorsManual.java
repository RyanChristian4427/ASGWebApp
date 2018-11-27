package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorsManual {
    private Long id;
    private String candidate_number;
    private Long instructor_id;
    private Date submitted_date;
    private Date pass_date;
}
