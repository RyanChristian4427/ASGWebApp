package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommendations {
    private Long id;
    private String candidate_number;
    private Date asg_recommend_date;
    private Date flight_competence_date;
    private Date application_data_date;
    private Date application_date;
    private Date caa_approval_date;
    private Date overall_comments_approval_by_caa;
}
