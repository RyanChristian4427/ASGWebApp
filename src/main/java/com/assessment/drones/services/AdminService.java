package com.assessment.drones.services;

import com.assessment.drones.domain.*;

public interface AdminService {
    String addFlyTraining(FlightTrainingDto flightTrainingDto);

    String addGroundSchool(GroundSchool groundSchool);

    String addOperatorsManual(OperatorsManual operatorsManual);

    String addFlightAssessment(FlightAssessment flightAssessment);

    String addRecommendations(Recommendations recommendations);

    OperatorsManual findOperatorManualByInstructorAndCandidate(long instructorId, long candidateId);

    FlightAssessment findFlightAssessment(long candidate_number, long instructor_id);
    
}
