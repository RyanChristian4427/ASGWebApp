package com.assessment.drones.repository;

import com.assessment.drones.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class AdminRepositoryJdbc implements AdminRepository{
    private JdbcTemplate jdbcTemplate;
    private RowMapper<FlyTraining> flyTrainingRowMapper;
    private RowMapper<GroundSchool> groundSchoolRowMapper;
    private RowMapper<OperatorsManual> operatorsManualRowMapper;
    private RowMapper<FlightAssessment> flightAssessmentRowMapper;
    private RowMapper<Recommendations> recommendationsRowMapper;

    @Autowired
    public AdminRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        flyTrainingRowMapper = (rs, i) -> new FlyTraining(
                rs.getLong("id"),
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("type"),
                rs.getDate("skills_date")
        );

        groundSchoolRowMapper = (rs, i) -> new GroundSchool(
                rs.getLong("id"),
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getDate("completion_date"),
                rs.getLong("question_bank"),
                rs.getDate("pass_date"),
                rs.getLong("pass_result"),
                rs.getLong("resit")
        );

        operatorsManualRowMapper = (rs, i) -> new OperatorsManual(
                rs.getLong("id"),
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getDate("submitted_date"),
                rs.getDate("pass_date")
        );

        flightAssessmentRowMapper = (rs, i) -> new FlightAssessment(
                rs.getLong("id"),
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("insurance"),
                rs.getString("logged-hours"),
                rs.getString("suas_category"),
                rs.getDate("assessment_pass_date")
        );

        recommendationsRowMapper = (rs, i) -> new Recommendations(
                rs.getLong("id"),
                rs.getString("candidate_number"),
                rs.getDate("asg_recommend_date"),
                rs.getDate("flight_competence_date"),
                rs.getDate("application_data_date"),
                rs.getDate("application_date"),
                rs.getDate("caa_approval_date"),
                rs.getDate("overall_comments_approval_by_caa")
        );
    }

    @Override
    public Integer addFlyTraining(FlyTraining flyTraining){
        ArrayList<Object> params = new ArrayList<>();
        params.add(flyTraining.getCandidate_number());
        params.add(flyTraining.getInstructor_id());
        params.add(flyTraining.getType());
        params.add(flyTraining.getSkills_date());
        return jdbcTemplate.update(
                "INSERT INTO flying_training (candidate_number, instructor_id, type, skills_date) " +
                        "VALUES(?, ?, ?, ?)",
                params.toArray());
    }

    @Override
    public Integer addGroundSchool(GroundSchool groundSchool){
        ArrayList<Object> params = new ArrayList<>();
        params.add(groundSchool.getCandidate_number());
        params.add(groundSchool.getInstructor_id());
        params.add(groundSchool.getCompletion_date());
        params.add(groundSchool.getQuestion_bank());
        params.add(groundSchool.getPass_date());
        params.add(groundSchool.getPass_result());
        params.add(groundSchool.getResit());
        return jdbcTemplate.update(
                "INSERT INTO ground_school (candidate_number, instructor_id, completion_date, question_bank, pass_result, pass_date, resit) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)",
                params.toArray());
    }

    @Override
    public Integer addOperatorsManual(OperatorsManual operatorsManual){
        ArrayList<Object> params = new ArrayList<>();
        params.add(operatorsManual.getCandidate_number());
        params.add(operatorsManual.getInstructor_id());
        params.add(operatorsManual.getSubmitted_date());
        params.add(operatorsManual.getPass_date());
        return jdbcTemplate.update(
                "INSERT INTO ground_school (candidate_number, instructor_id, submitted_date, pass_date) " +
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
}
