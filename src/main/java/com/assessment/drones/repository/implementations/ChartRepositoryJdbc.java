package com.assessment.drones.repository.implementations;

import com.assessment.drones.domain.FlightAssessmentDto;
import com.assessment.drones.domain.GroundSchoolDto;
import com.assessment.drones.domain.OperatorsManualDto;
import com.assessment.drones.repository.interfaces.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ChartRepositoryJdbc implements ChartRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<FlightAssessmentDto> flightAssessmentRowMapper;
    private RowMapper<GroundSchoolDto> groundSchoolRowMapper;
    private RowMapper<OperatorsManualDto> operatorsManualRowMapper;



    @Autowired
    public ChartRepositoryJdbc(JdbcTemplate aTemplate) {

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
                rs.getLong("instructor_id")
        );


    }



    @Override
    public Integer findAmountOfCandidates() {
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
