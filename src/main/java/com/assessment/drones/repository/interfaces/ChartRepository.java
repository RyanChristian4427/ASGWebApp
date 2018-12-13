package com.assessment.drones.repository.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface ChartRepository {


    Integer findAmountOfFlightAssessment();

    Integer findAmountOfGroundSchool();

    Integer findAmountOfOperationsManual();
}
