package com.assessment.drones.services;

import com.assessment.drones.domain.FlyTraining;
import com.assessment.drones.domain.GroundSchool;

public interface AdminService {
    String addFlyTraining(FlyTraining flyTraining);

    String addGroundSchool(GroundSchool groundSchool);
}
