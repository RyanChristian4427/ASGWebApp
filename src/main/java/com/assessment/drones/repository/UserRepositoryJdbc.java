package com.assessment.drones.repository;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                    "SELECT email, password, access_level FROM user WHERE user.email = ?",
                    new Object[]{emailAddress},
                    userMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer saveUser(RegistrationDto accountDto) {
//        ArrayList<Object> user = new ArrayList<>();
////        user.add(accountDto.getEmailAddress());
////        user.add(passwordEncoder().encode(accountDto.getPassword()));
////        user.add("USER");
        KeyHolder holder = new GeneratedKeyHolder();
//        return (jdbcTemplate.update(
//                "INSERT INTO user(email, password, access_level) VALUES(?, ?, ?)",
//                user.toArray()));

        this.jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection)
                            throws SQLException {
                        PreparedStatement pstmt = connection.prepareStatement(
                                "INSERT INTO user(email, password, access_level) VALUES(?, ?, ?)",
                                new String[] {"UserNameId"});
                        pstmt.setString(1, accountDto.getEmailAddress());
                        pstmt.setString(2, passwordEncoder().encode(accountDto.getPassword()));
                        pstmt.setString(3, "USER");
//                        pstmt.setBoolean(4,1);
                        return pstmt;
                    }
                }, holder);
//        Number key = holder.getKey();
//        userName.setUserNameId(key.intValue());
        return 1;
//                jdbcTemplate.update(
//                ""
//                );
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
