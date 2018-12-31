package com.assessment.asg.repository.implementations;

import com.assessment.asg.domain.*;
import com.assessment.asg.domain.courseProgress.*;
import com.assessment.asg.repository.interfaces.AdminRepository;
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
    private RowMapper<FlightTrainingDto> flyTrainingRowMapper;
    private RowMapper<GroundSchoolDto> groundSchoolRowMapper;
    private RowMapper<OperatorsManualDto> operatorsManualRowMapper;
    private RowMapper<FlightAssessmentDto> flightAssessmentRowMapper;
    private RowMapper<RecommendationsDto> recommendationsRowMapper;
    private RowMapper<Candidate> candidateRowMapper;

    //TODO get instructor id from logged in user

    @Autowired
    public AdminRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        flyTrainingRowMapper = (rs, i) -> new FlightTrainingDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("type"),
                rs.getDate("skills_date").toLocalDate()
        );

        groundSchoolRowMapper = (rs, i) -> new GroundSchoolDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getDate("completion_date").toLocalDate(),
                rs.getLong("question_bank"),
                rs.getLong("pass_result"),
                rs.getBoolean("resit")
        );

        operatorsManualRowMapper = (rs, i) -> new OperatorsManualDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getString("file_path")
        );

        flightAssessmentRowMapper = (rs, i) -> new FlightAssessmentDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getBoolean("insurance"),
                rs.getDouble("logged-hours"),
                rs.getString("suas_category")
        );

        recommendationsRowMapper = (rs, i) -> new RecommendationsDto(
                rs.getString("candidate_number"),
                rs.getDate("asg_recommend_date").toLocalDate(),
                rs.getDate("flight_competence_date").toLocalDate(),
                rs.getDate("application_date").toLocalDate(),
                rs.getDate("caa_approval_date").toLocalDate(),
                rs.getDate("overall_comments_approval_by_caa").toLocalDate()
        );

        //TODO map a SP for the progress stages
//        candidateRowMapper = ((rs, i) ->  new CandidateList(
//                new Candidate(rs.getString("reference_number"),
//                rs.getString("user_id")),
//                rs.getString("firstName" + " " + "surname"),
//                rs.getInt()
//        ));
    }

//    @Override
//    public List<CandidateList> getCandidateList() {
//        return jdbcTemplate.query("SELECT reference_number, first_name, surname FROM candidate", candidateRowMapper);
//    }


    @Override
    public Integer saveFlightTraining(FlightTrainingDto flightTrainingDto){
        return jdbcTemplate.update(
                "INSERT INTO flight_training (candidate_number, instructor_id, training_type, " +
                        "skills_assessment_date) VALUES(?, ?, ?, ?)", flightTrainingDto.getCandidateNumber(),
                flightTrainingDto.getInstructorId(), flightTrainingDto.getTrainingType(), flightTrainingDto.getSkillsDate());
    }

    @Override
    public Integer saveGroundSchool(GroundSchoolDto groundSchoolDto){
        int resit = 0;
        if(groundSchoolDto.getResit()) {
            resit = 1;
        }
        return jdbcTemplate.update(
                "INSERT INTO ground_school (candidate_number, instructor_id, completion_date, question_bank, " +
                        "pass_date, pass_result, resit) VALUES(?, ?, ?, ?, ?, ?, ?)", groundSchoolDto.getCandidateNumber(),
                groundSchoolDto.getInstructorId(), groundSchoolDto.getCompletionDate(), groundSchoolDto.getQuestionBank(),
                LocalDate.now(), groundSchoolDto.getPassResult(), resit);
    }

    @Override
    public Integer addOperatorsManual(OperatorsManualDto operatorsManualDto){
        return jdbcTemplate.update(
                "UPDATE operators_manual SET instructor_id = ?, pass_date = ? " +
                        "WHERE candidate_number = ?", operatorsManualDto.getInstructorId(), LocalDate.now(),
                operatorsManualDto.getCandidateNumber());
    }

    @Override
    public Integer addFlightAssessment(FlightAssessmentDto flightAssessmentDto){
        return jdbcTemplate.update(
                "INSERT INTO flight_assessment (candidate_number, instructor_id, insurance, logged_hours, " +
                        "suas_category, assessment_pass_date) VALUES(?, ?, ?, ?, ?, ?)",
                flightAssessmentDto.getCandidateNumber(), flightAssessmentDto.getInstructorId(), flightAssessmentDto.getInsurance(),
                flightAssessmentDto.getLoggedHours(), flightAssessmentDto.getSuasCategory(), LocalDate.now());
    }

    @Override
    public Integer addRecommendations(RecommendationsDto recommendationsDto){
        return jdbcTemplate.update(
                "INSERT INTO recommendations (candidate_number, asg_recomend_date, flight_competence_date, " +
                        "caa_application_date, caa_approval_date, overall_comments_approval_by_caa) VALUES(?, ?, ?, ?, ?, ?)",
                recommendationsDto.getCandidateNumber(), recommendationsDto.getAsgRecommendDate(),
                recommendationsDto.getFlightCompetenceDate(), recommendationsDto.getCaaApplicationDate(),
                recommendationsDto.getCaaApprovalDate(), recommendationsDto.getAsgOverallCommentsAndApprovalByCaa());
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

