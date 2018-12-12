package com.assessment.drones.repository.implementations;

import com.assessment.drones.domain.Instructor;
import com.assessment.drones.repository.interfaces.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InstructorRepositoryImpl implements InstructorRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Instructor> instructorRowMapper;

    @Autowired
    public InstructorRepositoryImpl(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        instructorRowMapper = (rs, i) -> new Instructor(
                rs.getLong("id"),
                rs.getString("user_id")
        );
    }

    @Override
    public Optional<Instructor> findInstructorByID(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            "SELECT * FROM instructor WHERE id = ?",
                            new Object[]{id},
                            instructorRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}