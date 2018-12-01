package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroundSchoolDto {
    private String candidateNumber;
    private Long instructorId;
    private LocalDate completionDate;
    private Long questionBank;
    private Long passResult;
    private Boolean resit;
}
