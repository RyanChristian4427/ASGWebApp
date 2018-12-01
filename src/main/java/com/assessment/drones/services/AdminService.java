package com.assessment.drones.services;

import com.assessment.drones.domain.*;

import java.util.Optional;

public interface AdminService {
    String addFlyTraining(FlightTrainingDto flightTrainingDto);

    String addGroundSchool(GroundSchoolDto groundSchoolDto);

    String addOperatorsManual(OperatorsManualDto operatorsManualDto);

    String addFlightAssessment(FlightAssessmentDto flightAssessmentDto);

    String addRecommendations(RecommendationsDto recommendationsDto);

    Optional<Candidate> findManualByCandidate(String candidateNumber);

    Boolean verify(Object formDto);

    FlightAssessmentDto findFlightAssessment(long candidate_number, long instructor_id);
    
}
