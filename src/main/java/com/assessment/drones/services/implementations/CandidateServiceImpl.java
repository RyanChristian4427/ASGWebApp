package com.assessment.drones.services.implementations;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.registration.CourseRegistrationDto;
import com.assessment.drones.domain.User;
import com.assessment.drones.repository.interfaces.CandidateRepository;
import com.assessment.drones.services.interfaces.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public User registerNewCandidate(CourseRegistrationDto accountDto) {
//        accountDto.setPassword(passwordEncoder().encode(accountDto.getPassword()));

        String previousCandidateReferenceNumber = candidateRepository.previousCandidateReferenceNumber();
        String[] referenceNumberParts = previousCandidateReferenceNumber.split("-");
        LocalDate localDate = LocalDate.now();
        String newReferenceNumber;
        if(Integer.toString(localDate.getMonthValue()).equals(referenceNumberParts[3])) {
            String newUniqueNum = String.format("%03d", Integer.parseInt(referenceNumberParts[1])+ 1);
            newReferenceNumber = "ASG-" + newUniqueNum + "-" + Integer.toString(localDate.getYear()-2000)
                    + "-" + (Integer.toString(localDate.getMonthValue()));
        } else {
            newReferenceNumber = "ASG-" + "000" + "-" + Integer.toString(localDate.getYear()-2000)
                    + "-" + (Integer.toString(localDate.getMonthValue()));
        }

        Integer insertResponse = candidateRepository.saveUser(accountDto, newReferenceNumber);

        if (insertResponse == 1) {
            return new User("test", "test", "candidate", false, true);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Candidate> findManualByCandidate(String candidateNumber) {
        return candidateRepository.findCandidateByNumber(candidateNumber);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
