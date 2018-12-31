package com.assessment.asg.repository.implementations;

import com.assessment.asg.domain.courseProgress.FlightAssessmentDto;
import com.assessment.asg.domain.courseProgress.GroundSchoolDto;
import com.assessment.asg.domain.courseProgress.OperatorsManualDto;
import com.assessment.asg.repository.interfaces.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ChartRepositoryImpl implements ChartRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<FlightAssessmentDto> flightAssessmentRowMapper;
    private RowMapper<GroundSchoolDto> groundSchoolRowMapper;
    private RowMapper<OperatorsManualDto> operatorsManualRowMapper;



    @Autowired
    public ChartRepositoryImpl(JdbcTemplate aTemplate) {

        jdbcTemplate = aTemplate;

        flightAssessmentRowMapper = (rs, i) -> new FlightAssessmentDto(
                rs.getString("candidate_number"),
                rs.getLong("instructor_id"),
                rs.getBoolean("insurance"),
                rs.getDouble("logged-hours"),
                rs.getString("suas_category")
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
    }

    @Override
    public Integer findAmountOfFlightAssessment() {
        return jdbcTemplate.query("SELECT COUNT(candidate_number) AS numberOfRows FROM flight_assessment", rs -> {
            if (rs.next()) {
                return rs.getInt("numberOfRows");
            } else {
                return null;
            }
        });
    }

    @Override
    public Integer findAmountOfGroundSchool() {
        return jdbcTemplate.query("SELECT COUNT(candidate_number) AS numberOfRows FROM ground_school;", rs -> {
            if (rs.next()) {
                return rs.getInt("numberOfRows");
            } else {
                return null;
            }
        });
    }

    @Override
    public Integer findAmountOfOperationsManual() {
        return jdbcTemplate.query("SELECT COUNT(candidate_number) AS numberOfRows FROM operators_manual;", rs -> {
            if (rs.next()) {
                return rs.getInt("numberOfRows");
            } else {
                return null;
            }
        });
    }


}
