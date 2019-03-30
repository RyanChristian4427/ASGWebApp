package com.assessment.asg.db.interfaces;

import com.assessment.asg.models.Candidate;
import com.assessment.asg.models.courseProgress.OperatorsManualDto;
import com.assessment.asg.models.registration.CourseRegistrationDto;
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
