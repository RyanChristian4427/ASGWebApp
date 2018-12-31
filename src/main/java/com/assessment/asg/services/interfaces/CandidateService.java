package com.assessment.asg.services.interfaces;

import com.assessment.asg.domain.Candidate;
import com.assessment.asg.domain.courseProgress.OperatorsManualDto;
import com.assessment.asg.domain.registration.CourseRegistrationDto;
import com.assessment.asg.domain.User;

import java.util.Optional;

public interface CandidateService {

    User registerNewCandidate(CourseRegistrationDto accountDto);

    Optional<Candidate> findCandidateByCurrentUser();

    void saveOperatorsManual(OperatorsManualDto operatorsManualDto);
}
