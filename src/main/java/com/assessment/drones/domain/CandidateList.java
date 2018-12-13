package com.assessment.drones.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO Remove this class, only exists as a temp work around for Marlowe not doing his work
public class CandidateList {
    public Candidate candidate;
    private String name;
    private int progressStage;
}
