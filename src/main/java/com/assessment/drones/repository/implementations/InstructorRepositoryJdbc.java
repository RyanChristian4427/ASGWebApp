package com.assessment.drones.repository.implementations;

import com.assessment.drones.domain.Instructor;
import com.assessment.drones.repository.interfaces.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class InstructorRepositoryJdbc implements InstructorRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Instructor> instructorRowMapper;

    @Autowired
    public InstructorRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        instructorRowMapper = (rs, i) -> new Instructor(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("surname")
        );
    }

    @Override
    public Optional<Instructor> findInstructorByID(Long id) {
        return Optional.of(
                Objects.requireNonNull(jdbcTemplate.queryForObject(
                        "SELECT * FROM instructor WHERE id = ?",
                        new Object[]{id},
                        instructorRowMapper)));
    }
}
