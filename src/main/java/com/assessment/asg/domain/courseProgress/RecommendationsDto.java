package com.assessment.asg.domain.courseProgress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationsDto {
    private String candidateNumber;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate asgRecommendDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate flightCompetenceDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate caaApplicationDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate caaApprovalDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate asgOverallCommentsAndApprovalByCaa;
}
