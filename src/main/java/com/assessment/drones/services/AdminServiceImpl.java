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
    public String addFlyTraining(FlightTrainingDto flightTrainingDto){
        Integer response = adminRepository.saveFlightTraining(flightTrainingDto);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String addGroundSchool(GroundSchoolDto groundSchoolDto){
        Integer response = adminRepository.saveGroundSchool(groundSchoolDto);

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

    @Override
    public OperatorsManual findOperatorManualByInstructorAndCandidate(long instructorId, long candidateId) {
        return this.adminRepository.findOperatorManualByInstructorAndCandidate(instructorId, candidateId);
    }

    @Override
    public FlightAssessment findFlightAssessment(long candidate_number, long instructor_id) {
        return this.adminRepository.findFlightAssessment(candidate_number, instructor_id);
    }
}
