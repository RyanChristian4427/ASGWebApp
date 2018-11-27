package com.assessment.drones.repository;

import com.assessment.drones.domain.*;
import org.springframework.stereotype.Component;

@Component
public interface AdminRepository {
    Integer addFlyTraining (FlyTraining flyTraining);

    Integer addGroundSchool (GroundSchool groundSchool);

    Integer addOperatorsManual (OperatorsManual operatorsManual);

    Integer addFlightAssessment (FlightAssessment flightAssessment);

    Integer addRecommendations (Recommendations recommendations);
}
