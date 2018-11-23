package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Review {

    private Long id;
    private String candidateNumber;
    private Long instructorID;
    private String reviewText;
}
