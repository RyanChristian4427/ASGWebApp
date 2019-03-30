package com.assessment.asg.services.interfaces;

import com.assessment.asg.models.courseProgress.*;

public interface AdminService {
    String saveFlightTraining(FlightTrainingDto flightTrainingDto);

    String saveGroundSchool(GroundSchoolDto groundSchoolDto);

    String saveOperatorsManual(OperatorsManualDto operatorsManualDto);

    String saveFlightAssessment(FlightAssessmentDto flightAssessmentDto);

    String saveRecommendations(RecommendationsDto recommendationsDto);

    Boolean verify(Object formDto);
}
