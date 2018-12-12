package com.assessment.drones.repository.interfaces;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.CandidateList;
import com.assessment.drones.domain.courseProgress.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AdminRepository {
    Integer saveFlightTraining(FlightTrainingDto flightTrainingDto);

    Integer saveGroundSchool(GroundSchoolDto groundSchoolDto);

    Integer addOperatorsManual (OperatorsManualDto operatorsManualDto);

    Integer addFlightAssessment (FlightAssessmentDto flightAssessmentDto);

    Integer addRecommendations (RecommendationsDto recommendationsDto);

    Optional<OperatorsManualDto> findOperationsManual(String candidateNumber);

//    List<CandidateList> getCandidateList();
}
