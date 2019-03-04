package com.assessment.asg.repository.interfaces;

import com.assessment.asg.domain.courseProgress.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AdminRepository {
    Integer saveFlightTraining(FlightTrainingDto flightTrainingDto);

    Integer saveGroundSchool(GroundSchoolDto groundSchoolDto);

    Integer addOperatorsManual(OperatorsManualDto operatorsManualDto);

    Integer addFlightAssessment(FlightAssessmentDto flightAssessmentDto);

    Integer addRecommendations(RecommendationsDto recommendationsDto);

    Optional<OperatorsManualDto> findOperationsManual(String candidateNumber);
}
