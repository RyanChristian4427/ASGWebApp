package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationsDto {
    private String candidateNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate asgRecommendDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate flightCompetenceDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate caaApplicationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate caaApprovalDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate asgOverallCommentsAndApprovalByCaa;
}
