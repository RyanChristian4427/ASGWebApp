package com.assessment.drones.repository;

import com.assessment.drones.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryJdbc implements UserRepository{

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper;

    @Autowired
    public UserRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

//        userMapper = (rs, i) -> new User(
//                rs.getString("email"),
//                rs.getString("first_name"),
//                rs.getString("surname"),
//                rs.getBoolean("access_level")
//        );

        userMapper = (rs, i) -> new User(
                rs.getString("email"),
                "Yes",
                "No",
                rs.getString("access_level")
        );
    }

    @Override
    public User findUserByEmail(String emailAddress) {
        return jdbcTemplate.queryForObject(
                "SELECT email, access_level FROM user " +
                        "WHERE email = ?",
                new Object[]{emailAddress},
                userMapper);
    }

//    "SELECT email, first_name, surname, access_level FROM candidate " +
//            "INNER JOIN user ON candidate.user_id=user.id " +
//            "WHERE user.email = ?"


}
