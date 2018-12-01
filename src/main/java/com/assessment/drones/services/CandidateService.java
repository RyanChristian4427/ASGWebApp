package com.assessment.drones.services;

import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.domain.User;

public interface CandidateService {

    User registerNewCandidate(RegistrationDto accountDto);
}
