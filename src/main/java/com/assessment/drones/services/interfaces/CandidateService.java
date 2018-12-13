package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.courseProgress.OperatorsManualDto;
import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.domain.User;

import java.security.Principal;
import java.util.Optional;

public interface CandidateService {

    User registerNewCandidate(CourseRegistrationDto accountDto);

    Optional<Candidate> findCandidateByCurrentUser();

    void saveOperatorsManual(OperatorsManualDto operatorsManualDto);
}
