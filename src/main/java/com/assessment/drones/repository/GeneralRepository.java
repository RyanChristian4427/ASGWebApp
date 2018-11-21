package com.assessment.drones.repository;

import com.assessment.drones.domain.GeneralInfo;
import org.springframework.stereotype.Component;

@Component
public interface GeneralRepository {
    Integer addGeneral(GeneralInfo general);
}
