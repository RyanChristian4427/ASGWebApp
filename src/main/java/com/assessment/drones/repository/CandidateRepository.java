package com.assessment.drones.repository;

import com.assessment.drones.domain.RegistrationDto;
import org.springframework.stereotype.Component;

@Component
public interface CandidateRepository {

    Integer saveUser(RegistrationDto accountDto,String newReferenceNumber);

    String previousCandidateReferenceNumber();
}
