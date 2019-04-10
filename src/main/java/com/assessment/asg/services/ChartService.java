package com.assessment.asg.services;

import com.assessment.asg.db.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface ChartService {

    Integer findAmountOfFlightAssessment();

    Integer findAmountOfGroundSchool();

    Integer findAmountOfOperationsManual();
}

@Service
class ChartServiceImpl implements ChartService {
    private ChartRepository chartRepository;

    @Autowired
    public ChartServiceImpl(final ChartRepository chartRepository) {
        this.chartRepository = chartRepository;
    }

    @Override
    public Integer findAmountOfFlightAssessment() {
        return chartRepository.findAmountOfFlightAssessment();
    }

    @Override
    public Integer findAmountOfGroundSchool() {
        return chartRepository.findAmountOfGroundSchool();
    }

    @Override
    public Integer findAmountOfOperationsManual() {
        return chartRepository.findAmountOfOperationsManual();
    }
}
