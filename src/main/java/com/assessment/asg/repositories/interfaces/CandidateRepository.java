package com.assessment.asg.repositories.interfaces;

import com.assessment.asg.domain.Candidate;
import com.assessment.asg.domain.courseProgress.OperatorsManualDto;
import com.assessment.asg.domain.registration.CourseRegistrationDto;
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
