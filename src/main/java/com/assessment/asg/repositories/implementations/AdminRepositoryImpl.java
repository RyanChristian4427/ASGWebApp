package com.assessment.asg.repositories.implementations;

import com.assessment.asg.domain.courseProgress.*;
import com.assessment.asg.repositories.interfaces.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<OperatorsManualDto> operatorsManualRowMapper;

    //TODO get instructor id from logged in user

    @Autowired
    public AdminRepositoryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        operatorsManualRowMapper = (rs, i) -> new OperatorsManualDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("file_path")
        );
    }

    @Override
    public Integer saveFlightTraining(final FlightTrainingDto flightTrainingDto) {
        return jdbcTemplate.update(
                "INSERT INTO flight_training (candidate_number, instructor_id, training_type, " +
                        "skills_assessment_date) VALUES(?, ?, ?, ?)", flightTrainingDto.getCandidateNumber(),
                flightTrainingDto.getInstructorId(), flightTrainingDto.getTrainingType(), flightTrainingDto.getSkillsDate());
    }

    @Override
    public Integer saveGroundSchool(final GroundSchoolDto groundSchoolDto) {
        int resit = 0;
        if (groundSchoolDto.getResit()) {
            resit = 1;
        }
        return jdbcTemplate.update(
                "INSERT INTO ground_school (candidate_number, instructor_id, completion_date, question_bank, " +
                        "pass_date, pass_result, resit) VALUES(?, ?, ?, ?, ?, ?, ?)", groundSchoolDto.getCandidateNumber(),
                groundSchoolDto.getInstructorId(), groundSchoolDto.getCompletionDate(), groundSchoolDto.getQuestionBank(),
                LocalDate.now(), groundSchoolDto.getPassResult(), resit);
    }

    @Override
    public Integer addOperatorsManual(final OperatorsManualDto operatorsManualDto) {
        return jdbcTemplate.update(
                "UPDATE operators_manual SET instructor_id = ?, pass_date = ? " +
                        "WHERE candidate_number = ?", operatorsManualDto.getInstructorId(), LocalDate.now(),
                operatorsManualDto.getCandidateNumber());
    }

    @Override
    public Integer addFlightAssessment(final FlightAssessmentDto flightAssessmentDto) {
        return jdbcTemplate.update(
                "INSERT INTO flight_assessment (candidate_number, instructor_id, insurance, logged_hours, " +
                        "suas_category, assessment_pass_date) VALUES(?, ?, ?, ?, ?, ?)",
                flightAssessmentDto.getCandidateNumber(), flightAssessmentDto.getInstructorId(), flightAssessmentDto.getInsurance(),
                flightAssessmentDto.getLoggedHours(), flightAssessmentDto.getSuasCategory(), LocalDate.now());
    }

    @Override
    public Integer addRecommendations(final RecommendationsDto recommendationsDto) {
        return jdbcTemplate.update(
                "INSERT INTO recommendations (candidate_number, asg_recomend_date, flight_competence_date, " +
                        "caa_application_date, caa_approval_date, overall_comments_approval_by_caa) VALUES(?, ?, ?, ?, ?, ?)",
                recommendationsDto.getCandidateNumber(), recommendationsDto.getAsgRecommendDate(),
                recommendationsDto.getFlightCompetenceDate(), recommendationsDto.getCaaApplicationDate(),
                recommendationsDto.getCaaApprovalDate(), recommendationsDto.getAsgOverallCommentsAndApprovalByCaa());
    }

    @Override
    public Optional<OperatorsManualDto> findOperationsManual(final String candidateNumber) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            "SELECT * FROM operators_manual WHERE candidate_number = ?",
                            new Object[]{candidateNumber},
                            operatorsManualRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}

