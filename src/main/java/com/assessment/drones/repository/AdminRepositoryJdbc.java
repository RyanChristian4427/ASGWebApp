package com.assessment.drones.repository;

import com.assessment.drones.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AdminRepositoryJdbc implements AdminRepository{

    private JdbcTemplate jdbcTemplate;
    private RowMapper<FlightTrainingDto> flyTrainingRowMapper;
    private RowMapper<GroundSchoolDto> groundSchoolRowMapper;
    private RowMapper<OperatorsManualDto> operatorsManualRowMapper;
    private RowMapper<FlightAssessmentDto> flightAssessmentRowMapper;
    private RowMapper<RecommendationsDto> recommendationsRowMapper;

    @Autowired
    public AdminRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        flyTrainingRowMapper = (rs, i) -> new FlightTrainingDto(
                rs.getLong("id"),
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("type"),
                rs.getDate("skills_date")
        );

        groundSchoolRowMapper = (rs, i) -> new GroundSchoolDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getDate("completion_date"),
                rs.getLong("question_bank"),
                rs.getLong("pass_result"),
                rs.getBoolean("resit")
        );

        operatorsManualRowMapper = (rs, i) -> new OperatorsManualDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id")
        );

        flightAssessmentRowMapper = (rs, i) -> new FlightAssessmentDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getBoolean("insurance"),
                rs.getDouble("logged-hours"),
                rs.getString("suas_category")
        );

        recommendationsRowMapper = (rs, i) -> new RecommendationsDto(
                rs.getLong("id"),
                rs.getLong("candidate_number"),
                rs.getString("asg_recommend_date"),
                rs.getString("flight_competence_date"),
                rs.getString("application_data_date"),
                rs.getString("application_date"),
                rs.getString("caa_approval_date"),
                rs.getString("overall_comments_approval_by_caa")
        );
    }

    @Override
    public Integer saveFlightTraining(FlightTrainingDto flightTrainingDto){
        return jdbcTemplate.update(
                "INSERT INTO flight_training (candidate_number, instructor_id, training_type, " +
                        "skills_assessment_date) VALUES(?, ?, ?, ?)", flightTrainingDto.getCandidate_number(),
                flightTrainingDto.getInstructor_id(), flightTrainingDto.getTraining_type(), flightTrainingDto.getSkills_date());
    }

    @Override
    public Integer saveGroundSchool(GroundSchoolDto groundSchoolDto){
        int resit = 0;
        if(groundSchoolDto.getResit()) {
            resit = 1;
        }
        return jdbcTemplate.update(
                "INSERT INTO ground_school (candidate_number, instructor_id, completion_date, question_bank, " +
                        "pass_date, pass_result, resit) VALUES(?, ?, ?, ?, ?, ?, ?)", groundSchoolDto.getCandidate_number(),
                groundSchoolDto.getInstructor_id(), groundSchoolDto.getCompletion_date(), groundSchoolDto.getQuestion_bank(),
                LocalDate.now(), groundSchoolDto.getPass_result(), resit);
    }

    @Override
    public Integer addOperatorsManual(OperatorsManualDto operatorsManualDto){
        return jdbcTemplate.update(
                "UPDATE operators_manual SET instructor_id = ?, pass_date = ? " +
                        "WHERE candidate_number = ?", operatorsManualDto.getInstructor_id(), LocalDate.now(),
                operatorsManualDto.getCandidate_number());
    }

    @Override
    public Integer addFlightAssessment(FlightAssessmentDto flightAssessmentDto){
        return jdbcTemplate.update(
                "INSERT INTO flight_assessment (candidate_number, instructor_id, insurance, logged_hours, " +
                        "suas_category, assessment_pass_date) VALUES(?, ?, ?, ?, ?, ?)",
                flightAssessmentDto.getCandidate_number(), flightAssessmentDto.getInstructor_id(), flightAssessmentDto.getInsurance(),
                flightAssessmentDto.getLogged_hours(), flightAssessmentDto.getSuas_category(), LocalDate.now());
    }

    @Override
    public Integer addRecommendations(RecommendationsDto recommendationsDto){
        ArrayList<Object> params = new ArrayList<>();
        params.add(recommendationsDto.getCandidate_number());
        params.add(recommendationsDto.getAsg_recommend_date());
        params.add(recommendationsDto.getFlight_competence_date());
        params.add(recommendationsDto.getApplication_data_date());
        params.add(recommendationsDto.getApplication_date());
        params.add(recommendationsDto.getCaa_approval_date());
        params.add(recommendationsDto.getOverall_comments_approval_by_caa());
        return jdbcTemplate.update(
                "INSERT INTO recommendations (candidate_number, asg_recomend_date, flight_competence_date, application_data_date, application_date, caa_approval_date, overall_comments_approval_by_caa) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)",
                params.toArray());
    }

    @Override
    public FlightAssessmentDto findFlightAssessment(long candidate_number, long instructor_id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM flight_assessment WHERE instructor_id = ? AND candidate_number = ?",
                    new Object[]{instructor_id, candidate_number},
                    this.flightAssessmentRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Optional<OperatorsManualDto> findOperationsManual(String candidateNumber) {
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

