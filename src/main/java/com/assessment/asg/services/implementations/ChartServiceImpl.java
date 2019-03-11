package com.assessment.asg.services.implementations;

import com.assessment.asg.repositories.interfaces.ChartRepository;
import com.assessment.asg.services.interfaces.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChartServiceImpl implements ChartService {
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
