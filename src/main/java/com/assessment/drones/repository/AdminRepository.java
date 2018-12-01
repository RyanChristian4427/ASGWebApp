package com.assessment.drones.repository;

import com.assessment.drones.domain.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AdminRepository {
    Integer saveFlightTraining(FlightTrainingDto flightTrainingDto);

    Integer saveGroundSchool(GroundSchoolDto groundSchoolDto);

    Integer addOperatorsManual (OperatorsManualDto operatorsManualDto);

    Integer addFlightAssessment (FlightAssessment flightAssessment);

    Integer addRecommendations (Recommendations recommendations);

    Optional<OperatorsManualDto> findManualByCandidate(String candidateNumber);

    FlightAssessment findFlightAssessment(long candidate_number, long instructor_id);

}
