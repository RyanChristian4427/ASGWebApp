package com.assessment.asg.domain.courseProgress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroundSchoolDto {
    private String candidateNumber;
    private Long instructorId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate completionDate;
    private Long questionBank;
    private Long passResult;
    private Boolean resit;
}
