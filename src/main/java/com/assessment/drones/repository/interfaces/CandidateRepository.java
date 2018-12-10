package com.assessment.drones.repository.interfaces;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.registration.CourseRegistrationDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CandidateRepository {

    Integer saveUser(CourseRegistrationDto accountDto, String newReferenceNumber);

    Optional<Candidate> findCandidateByNumber(String candidateNumber);

    String previousCandidateReferenceNumber();
}
