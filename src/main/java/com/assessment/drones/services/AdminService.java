package com.assessment.drones.services;

import com.assessment.drones.domain.*;

import java.util.Optional;

public interface AdminService {
    String addFlyTraining(FlightTrainingDto flightTrainingDto);

    String addGroundSchool(GroundSchoolDto groundSchoolDto);

    String addOperatorsManual(OperatorsManualDto operatorsManualDto);

    String addFlightAssessment(FlightAssessment flightAssessment);

    String addRecommendations(Recommendations recommendations);

    Optional<OperatorsManualDto> findManualByCandidate(String candidateNumber);

    FlightAssessment findFlightAssessment(long candidate_number, long instructor_id);
    
}
