package com.assessment.drones.services;

import com.assessment.drones.domain.FlyTraining;
import com.assessment.drones.domain.GroundSchool;
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
}
