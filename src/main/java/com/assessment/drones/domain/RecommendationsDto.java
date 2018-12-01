package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationsDto {
    private Long id;
    private Long candidate_number;
    private String asg_recommend_date;
    private String flight_competence_date;
    private String application_data_date;
    private String application_date;
    private String caa_approval_date;
    private String overall_comments_approval_by_caa;
}
