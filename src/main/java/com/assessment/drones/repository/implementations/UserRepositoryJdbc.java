package com.assessment.drones.repository.implementations;

import com.assessment.drones.domain.User;
import com.assessment.drones.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryJdbc implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper;

    @Autowired
    public UserRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        userMapper = (rs, i) -> new User(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
        );
    }

    @Override
    public User findUserByEmail(String emailAddress) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT email, password, role FROM user WHERE user.email = ?",
                    new Object[]{emailAddress},
                    userMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
