package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    private String referenceNumber;
    private String userEmail;
}
