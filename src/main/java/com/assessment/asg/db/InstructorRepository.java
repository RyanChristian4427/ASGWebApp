package com.assessment.asg.db;

import com.assessment.asg.models.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public interface InstructorRepository {
    Optional<Instructor> findInstructorByID(Long id);
}

@Repository
class InstructorRepositoryImpl implements InstructorRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Instructor> instructorRowMapper;

    @Autowired
    public InstructorRepositoryImpl(final JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        instructorRowMapper = (rs, i) -> new Instructor(
                rs.getLong("id"),
                rs.getString("user_id")
        );
    }

    @Override
    public Optional<Instructor> findInstructorByID(final Long id) {
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