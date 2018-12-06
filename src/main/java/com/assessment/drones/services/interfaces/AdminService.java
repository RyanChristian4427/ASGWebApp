package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.*;

import java.util.List;

public interface AdminService {
    String saveFlightTraining(FlightTrainingDto flightTrainingDto);

    String saveGroundSchool(GroundSchoolDto groundSchoolDto);

    String saveOperationsManual(OperatorsManualDto operatorsManualDto);

    String saveFlightAssessment(FlightAssessmentDto flightAssessmentDto);

    String saveRecommendations(RecommendationsDto recommendationsDto);

    Boolean verify(Object formDto);

    List<Candidate> getCandidateList();
}
