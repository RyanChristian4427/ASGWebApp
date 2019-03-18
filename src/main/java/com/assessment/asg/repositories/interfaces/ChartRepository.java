package com.assessment.asg.repositories.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface ChartRepository {


    Integer findAmountOfFlightAssessment();

    Integer findAmountOfGroundSchool();

    Integer findAmountOfOperationsManual();
}
