package com.assessment.drones.domain.courseProgress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorsManualDto {
    private String candidateNumber;
    private Long instructorId;
}
