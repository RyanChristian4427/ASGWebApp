package com.assessment.drones.repository;

import com.assessment.drones.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class AdminRepositoryJdbc implements AdminRepository{

    private JdbcTemplate jdbcTemplate;
    private RowMapper<FlightTrainingDto> flyTrainingRowMapper;
    private RowMapper<GroundSchoolDto> groundSchoolRowMapper;
    private RowMapper<OperatorsManual> operatorsManualRowMapper;
    private RowMapper<FlightAssessment> flightAssessmentRowMapper;
    private RowMapper<Recommendations> recommendationsRowMapper;

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

        operatorsManualRowMapper = (rs, i) -> new OperatorsManual(
                rs.getLong("id"),
                rs.getLong("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("submitted_date"),
                rs.getString("pass_date")
        );

        flightAssessmentRowMapper = (rs, i) -> new FlightAssessment(
                rs.getLong("id"),
                rs.getLong("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("insurance"),
                rs.getString("logged-hours"),
                rs.getString("suas_category"),
                rs.getDate("assessment_pass_date")
        );

        recommendationsRowMapper = (rs, i) -> new Recommendations(
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
                        "pass_result, resit) VALUES(?, ?, ?, ?, ?, ?)", groundSchoolDto.getCandidate_number(),
                groundSchoolDto.getInstructor_id(), groundSchoolDto.getCompletion_date(), groundSchoolDto.getQuestion_bank(),
                groundSchoolDto.getPass_result(), resit);
    }

    @Override
    public Integer addOperatorsManual(OperatorsManual operatorsManual){
        ArrayList<Object> params = new ArrayList<>();
        params.add(operatorsManual.getCandidate_number());
        params.add(operatorsManual.getInstructor_id());
        params.add(operatorsManual.getSubmitted_date());
        params.add(operatorsManual.getPass_date());
        return jdbcTemplate.update(
                "INSERT INTO operators_manual (candidate_number, instructor_id, submitted_date, pass_date) " +
                        "VALUES(?, ?, ?, ?)",
                params.toArray());
    }

    @Override
    public Integer addFlightAssessment(FlightAssessment flightAssessment){
        ArrayList<Object> params = new ArrayList<>();
        params.add(flightAssessment.getCandidate_number());
        params.add(flightAssessment.getInstructor_id());
        params.add(flightAssessment.getInsurance());
        params.add(flightAssessment.getLogged_hours());
        params.add(flightAssessment.getSuas_category());
        params.add(flightAssessment.getAssessment_pass_date());
        return jdbcTemplate.update(
                "INSERT INTO flight_assessment (candidate_number, instructor_id, insurance, logged_hours, suas_category, assessment_pass_date) " +
                        "VALUES(?, ?, ?, ?, ?, ?)",
                params.toArray());
    }

    @Override
    public Integer addRecommendations(Recommendations recommendations){
        ArrayList<Object> params = new ArrayList<>();
        params.add(recommendations.getCandidate_number());
        params.add(recommendations.getAsg_recommend_date());
        params.add(recommendations.getFlight_competence_date());
        params.add(recommendations.getApplication_data_date());
        params.add(recommendations.getApplication_date());
        params.add(recommendations.getCaa_approval_date());
        params.add(recommendations.getOverall_comments_approval_by_caa());
        return jdbcTemplate.update(
                "INSERT INTO recommendations (candidate_number, asg_recomend_date, flight_competence_date, application_data_date, application_date, caa_approval_date, overall_comments_approval_by_caa) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)",
                params.toArray());
    }
    public OperatorsManual findOperatorManualByInstructorAndCandidate(long instructorId, long candidateId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM operators_manual WHERE instructor_id = ? AND candidate_number = ?",
                    new Object[]{instructorId, candidateId},
                    this.operatorsManualRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public FlightAssessment findFlightAssessment(long candidate_number, long instructor_id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM flight_assessment WHERE instructor_id = ? AND candidate_number = ?",
                    new Object[]{instructor_id, candidate_number},
                    this.flightAssessmentRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

