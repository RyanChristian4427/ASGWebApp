package com.assessment.drones.repository.interfaces;

import com.assessment.drones.domain.FlightAssessmentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChartRepository {


    Integer findAmountOfFlightAssessment();

    Integer findAmountOfGroundSchool();

    Integer findAmountOfOperationsManual();
}
