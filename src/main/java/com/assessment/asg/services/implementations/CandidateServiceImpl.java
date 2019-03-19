package com.assessment.asg.services.implementations;

import com.assessment.asg.config.DefaultUserDetails;
import com.assessment.asg.domain.Candidate;
import com.assessment.asg.domain.courseProgress.OperatorsManualDto;
import com.assessment.asg.domain.registration.CourseRegistrationDto;
import com.assessment.asg.domain.User;
import com.assessment.asg.repositories.interfaces.CandidateRepository;
import com.assessment.asg.services.interfaces.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public CandidateServiceImpl(final CandidateRepository candidateRepository, final UserDetailsServiceImpl userDetailsService) {
        this.candidateRepository = candidateRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public User registerNewCandidate(final CourseRegistrationDto registrationDto) {
        registrationDto.setEmailAddress(userDetailsService.getCurrentUserDetails().get().getUsername());
        registrationDto.setReferenceNumber(createReferenceNumberMonthYear());
        Integer insertResponse = candidateRepository.saveUser(registrationDto);

        if (insertResponse == 1) {
            return new User("test", "test", "candidate", false, true);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Candidate> findCandidateByCurrentUser() {
        Optional<DefaultUserDetails> currentUser = userDetailsService.getCurrentUserDetails();
        if (currentUser.isPresent()) {
            return candidateRepository.findCandidateByEmail(currentUser.get().getUsername());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void saveOperatorsManual(final OperatorsManualDto operatorsManualDto) {
       candidateRepository.saveOperatorsManual(operatorsManualDto);
    }


    private String createReferenceNumberMonthYear() {
        String[] referenceNumberParts = candidateRepository.previousCandidateReferenceNumber().split("-");
        LocalDate localDate = LocalDate.now();
        String newReferenceNumber;
        if (Integer.toString(localDate.getMonthValue()).equals(referenceNumberParts[3])) {
            String newUniqueNum = String.format("%03d", Integer.parseInt(referenceNumberParts[1]) + 1);
            newReferenceNumber = "ASG-" + newUniqueNum + "-" + (localDate.getYear() - 2000) +
                    "-" + (localDate.getMonthValue());
        } else {
            newReferenceNumber = "ASG-" + "000" + "-" + (localDate.getYear() - 2000) +
                    "-" + (localDate.getMonthValue());
        }
        return newReferenceNumber;
    }
}
