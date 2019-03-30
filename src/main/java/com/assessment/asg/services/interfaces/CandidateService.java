package com.assessment.asg.services.interfaces;

import com.assessment.asg.models.Candidate;
import com.assessment.asg.models.courseProgress.OperatorsManualDto;
import com.assessment.asg.models.registration.CourseRegistrationDto;
import com.assessment.asg.models.User;

import java.util.Optional;

public interface CandidateService {

    User registerNewCandidate(CourseRegistrationDto accountDto);

    Optional<Candidate> findCandidateByCurrentUser();

    void saveOperatorsManual(OperatorsManualDto operatorsManualDto);
}
