package com.assessment.drones.services;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.domain.User;

import java.util.Optional;

public interface CandidateService {

    User registerNewCandidate(RegistrationDto accountDto);

    Optional<Candidate> findManualByCandidate(String candidateNumber);
}
