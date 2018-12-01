package com.assessment.drones.services;

import com.assessment.drones.domain.*;
import com.assessment.drones.repository.AdminRepository;
import com.assessment.drones.repository.CandidateRepository;
import com.assessment.drones.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    private AdminRepository adminRepository;
    private CandidateRepository candidateRepository;
    private InstructorRepository instructorRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, CandidateRepository candidateRepository, InstructorRepository instructorRepository) {
        this.adminRepository = adminRepository;
        this.candidateRepository = candidateRepository;
        this.instructorRepository = instructorRepository;
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
    public String addOperatorsManual(OperatorsManualDto operatorsManualDto){
        Integer response = adminRepository.addOperatorsManual(operatorsManualDto);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String addFlightAssessment(FlightAssessmentDto flightAssessmentDto){
        Integer response = adminRepository.addFlightAssessment(flightAssessmentDto);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String addRecommendations(RecommendationsDto recommendationsDto){
        Integer response = adminRepository.addRecommendations(recommendationsDto);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public Optional<Candidate> findManualByCandidate(String candidateNumber) {
        return candidateRepository.findCandidateByNumber(candidateNumber);
    }

    @Override
    public Boolean verify(Object formDto){

        boolean verified = false;

        if(formDto instanceof FlightTrainingDto) {
            verified = candidateRepository.findCandidateByNumber(((FlightTrainingDto) formDto)
                    .getCandidateNumber()).isPresent() && instructorRepository.findInstructorByID(((FlightTrainingDto) formDto)
                    .getInstructorId()).isPresent();
            addFlyTraining((FlightTrainingDto) formDto);
        } else if(formDto instanceof GroundSchoolDto) {
            verified = candidateRepository.findCandidateByNumber(((GroundSchoolDto) formDto)
                    .getCandidateNumber()).isPresent() && instructorRepository.findInstructorByID(((GroundSchoolDto) formDto)
                    .getInstructorId()).isPresent();
            addGroundSchool((GroundSchoolDto) formDto);
        } else if (formDto instanceof OperatorsManualDto) {
            verified = candidateRepository.findCandidateByNumber(((OperatorsManualDto) formDto)
                    .getCandidateNumber()).isPresent() && instructorRepository.findInstructorByID(((OperatorsManualDto) formDto)
                    .getInstructorId()).isPresent() &&
                    adminRepository.findOperationsManual(((OperatorsManualDto) formDto).getCandidateNumber()).isPresent();
            addOperatorsManual((OperatorsManualDto) formDto);
        } else if (formDto instanceof FlightAssessmentDto) {
            verified = candidateRepository.findCandidateByNumber(((FlightAssessmentDto) formDto)
                    .getCandidateNumber()).isPresent() && instructorRepository.findInstructorByID(((FlightAssessmentDto) formDto)
                    .getInstructorId()).isPresent();
            addFlightAssessment((FlightAssessmentDto) formDto);
        } else if(formDto instanceof RecommendationsDto) {
            verified = candidateRepository.findCandidateByNumber(((RecommendationsDto) formDto)
                    .getCandidateNumber()).isPresent();
            addRecommendations((RecommendationsDto) formDto);
        }
        return verified;
    }

    @Override
    public FlightAssessmentDto findFlightAssessment(long candidate_number, long instructor_id) {
        return this.adminRepository.findFlightAssessment(candidate_number, instructor_id);
    }
}
