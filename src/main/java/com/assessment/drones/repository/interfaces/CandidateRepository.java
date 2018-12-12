package com.assessment.drones.repository.interfaces;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.courseProgress.OperatorsManualDto;
import com.assessment.drones.domain.registration.CourseRegistrationDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CandidateRepository {

    Integer saveUser(CourseRegistrationDto accountDto);

    Optional<Candidate> findCandidateByNumber(String candidateNumber);

    Optional<Candidate> findCandidateByEmail(String emailAddress);

    String previousCandidateReferenceNumber();

    void saveOperatorsManual(OperatorsManualDto operatorsManualDto);
}
