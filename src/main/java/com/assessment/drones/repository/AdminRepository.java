package com.assessment.drones.repository;

import com.assessment.drones.domain.*;
import org.springframework.stereotype.Component;

@Component
public interface AdminRepository {
    Integer saveFlightTraining(FlightTrainingDto flightTrainingDto);

    Integer addGroundSchool (GroundSchool groundSchool);

    Integer addOperatorsManual (OperatorsManual operatorsManual);

    Integer addFlightAssessment (FlightAssessment flightAssessment);

    Integer addRecommendations (Recommendations recommendations);

    OperatorsManual findOperatorManualByInstructorAndCandidate(long instructorId, long candidateId);

    FlightAssessment findFlightAssessment(long candidate_number, long instructor_id);

}
