package com.assessment.drones.repository;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepositoryJdbc implements UserRepository{

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper;

    @Autowired
    public UserRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        userMapper = (rs, i) -> new User(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("access_level")
        );
    }

    @Override
    public User findUserByEmail(String emailAddress) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT email, first_name, surname, access_level FROM candidate " +
                            "INNER JOIN user ON candidate.user_id=user.id " +
                            "WHERE user.email = ?",
                    new Object[]{emailAddress},
                    userMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer saveUser(UserDto accountDto) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(accountDto.getEmailAddress());
        params.add(accountDto.getPassword());
        params.add("USER");
        return jdbcTemplate.update(
                "INSERT INTO user(email, password, access_level) VALUES(?, ?, ?)",
                params.toArray());
    }




}
