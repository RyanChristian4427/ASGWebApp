package com.assessment.drones.domain.courseProgress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightTrainingDto {;
    private String candidateNumber;
    private Long instructorId;
    private String trainingType;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate skillsDate;
}
