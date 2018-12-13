package com.assessment.drones.services.interfaces;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.FlightAssessmentDto;

import java.util.List;

public interface ChartService {

    Integer findAmountOfFlightAssessment();

    Integer findAmountOfGroundSchool();

    Integer findAmountOfOperationsManual();

}
