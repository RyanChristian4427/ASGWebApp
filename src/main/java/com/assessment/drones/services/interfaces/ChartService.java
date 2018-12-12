package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.FlightAssessmentDto;

import java.util.List;

public interface ChartService {

    Integer findAmountOfCandidates();

    Integer findAmountOfGroundSchool();

    Integer findAmountOfOperationsManual();

}
