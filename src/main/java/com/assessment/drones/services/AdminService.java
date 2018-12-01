package com.assessment.drones.services;

import com.assessment.drones.domain.*;

public interface AdminService {
    String addFlyTraining(FlightTrainingDto flightTrainingDto);

    String addGroundSchool(GroundSchoolDto groundSchoolDto);

    String addOperatorsManual(OperatorsManualDto operatorsManualDto);

    String addFlightAssessment(FlightAssessment flightAssessment);

    String addRecommendations(Recommendations recommendations);

    OperatorsManualDto findOperatorManualByInstructorAndCandidate(long instructorId, long candidateId);

    FlightAssessment findFlightAssessment(long candidate_number, long instructor_id);
    
}
