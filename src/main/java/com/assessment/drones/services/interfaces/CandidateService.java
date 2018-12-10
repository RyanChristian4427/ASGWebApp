package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.domain.User;

import java.util.Optional;

public interface CandidateService {

    User registerNewCandidate(CourseRegistrationDto accountDto);

    Optional<Candidate> findCandidateByEmail(String emailAddress);
}
