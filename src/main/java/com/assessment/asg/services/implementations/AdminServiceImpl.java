package com.assessment.asg.services.implementations;

import com.assessment.asg.domain.courseProgress.*;
import com.assessment.asg.repositories.interfaces.AdminRepository;
import com.assessment.asg.repositories.interfaces.CandidateRepository;
import com.assessment.asg.repositories.interfaces.InstructorRepository;
import com.assessment.asg.services.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private CandidateRepository candidateRepository;
    private InstructorRepository instructorRepository;

    @Autowired
    public AdminServiceImpl(final AdminRepository adminRepository, final CandidateRepository candidateRepository,
                            final InstructorRepository instructorRepository) {
        this.adminRepository = adminRepository;
        this.candidateRepository = candidateRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public String saveFlightTraining(final FlightTrainingDto flightTrainingDto) {
        Integer response = adminRepository.saveFlightTraining(flightTrainingDto);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String saveGroundSchool(final GroundSchoolDto groundSchoolDto) {
        Integer response = adminRepository.saveGroundSchool(groundSchoolDto);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String saveOperatorsManual(final OperatorsManualDto operatorsManualDto) {
        Integer response = adminRepository.addOperatorsManual(operatorsManualDto);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String saveFlightAssessment(final FlightAssessmentDto flightAssessmentDto) {
        Integer response = adminRepository.addFlightAssessment(flightAssessmentDto);

        if (response == 1) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public String saveRecommendations(final RecommendationsDto recommendationsDto) {
        if (1 == adminRepository.addRecommendations(recommendationsDto)) {
            return "Insert Success";
        } else {
            return null;
        }
    }

    @Override
    public Boolean verify(final Object formDto) {

        boolean verified = false;

        //TODO Check to make sure the candidate numbers aren't already in each table, as it throws and error
        if (formDto instanceof FlightTrainingDto) {
            verified = candidateRepository.findCandidateByNumber(((FlightTrainingDto) formDto)
                    .getCandidateNumber()).isPresent() && instructorRepository.findInstructorByID(((FlightTrainingDto) formDto)
                    .getInstructorId()).isPresent();
            if (verified) {
                saveFlightTraining((FlightTrainingDto) formDto);
            }
        } else if (formDto instanceof GroundSchoolDto) {
            verified = candidateRepository.findCandidateByNumber(((GroundSchoolDto) formDto)
                    .getCandidateNumber()).isPresent() && instructorRepository.findInstructorByID(((GroundSchoolDto) formDto)
                    .getInstructorId()).isPresent();
            if (verified) {
                saveGroundSchool((GroundSchoolDto) formDto);
            }
        } else if (formDto instanceof OperatorsManualDto) {
            verified = candidateRepository.findCandidateByNumber(((OperatorsManualDto) formDto)
                    .getCandidateNumber()).isPresent() && instructorRepository.findInstructorByID(((OperatorsManualDto) formDto)
                    .getInstructorId()).isPresent() && adminRepository.findOperationsManual(((OperatorsManualDto) formDto).getCandidateNumber()).isPresent();
            if (verified) {
                saveOperatorsManual((OperatorsManualDto) formDto);
            }
        } else if (formDto instanceof FlightAssessmentDto) {
            verified = candidateRepository.findCandidateByNumber(((FlightAssessmentDto) formDto)
                    .getCandidateNumber()).isPresent() && instructorRepository.findInstructorByID(((FlightAssessmentDto) formDto)
                    .getInstructorId()).isPresent();
            if (verified) {
                saveFlightAssessment((FlightAssessmentDto) formDto);
            }
        } else if (formDto instanceof RecommendationsDto) {
            verified = candidateRepository.findCandidateByNumber(((RecommendationsDto) formDto)
                    .getCandidateNumber()).isPresent();
            if (verified) {
                saveRecommendations((RecommendationsDto) formDto);
            }
        }
        return verified;
    }
}
