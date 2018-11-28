package com.assessment.drones.services;

import com.assessment.drones.domain.*;
import com.assessment.drones.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository aAdminRepository) {
        adminRepository= aAdminRepository;
    }

    @Override
    public String addFlyTraining(FlyTraining flyTraining){
        Integer response = adminRepository.addFlyTraining(flyTraining);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String addGroundSchool(GroundSchool groundSchool){
        Integer response = adminRepository.addGroundSchool(groundSchool);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String addOperatorsManual(OperatorsManual operatorsManual){
        Integer response = adminRepository.addOperatorsManual(operatorsManual);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String addFlightAssessment(FlightAssessment flightAssessment){
        Integer response = adminRepository.addFlightAssessment(flightAssessment);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String addRecommendations(Recommendations recommendations){
        Integer response = adminRepository.addRecommendations(recommendations);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }
}
