package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.*;
import com.assessment.drones.domain.courseProgress.*;

import java.util.List;

public interface AdminService {
    String saveFlightTraining(FlightTrainingDto flightTrainingDto);

    String saveGroundSchool(GroundSchoolDto groundSchoolDto);

    String saveOperatorsManual(OperatorsManualDto operatorsManualDto);

    String saveFlightAssessment(FlightAssessmentDto flightAssessmentDto);

    String saveRecommendations(RecommendationsDto recommendationsDto);

    Boolean verify(Object formDto);

//    List<Candidate> getCandidateList();
}
