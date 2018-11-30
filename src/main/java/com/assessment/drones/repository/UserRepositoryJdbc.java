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

    @Override
    public Integer saveUser(RegistrationDto accountDto) {
        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO address(line_1, line_2, city, postcode) VALUES(?, ?, ?, ?)",
                        new String[] {"id"});
                pstmt.setString(1, accountDto.getAddressLine1());
                pstmt.setString(2, accountDto.getAddressLine2());
                pstmt.setString(3, accountDto.getCity());
                pstmt.setString(4, accountDto.getPostCode());
                return pstmt;
            }, holder);

        jdbcTemplate.update(connection -> {
                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO contact_info(phone_number, address_id) VALUES(?, ?)",
                        new String[] {"id"});
                pstmt.setString(1, accountDto.getPhoneNumber());
                pstmt.setLong(2, holder.getKey().longValue());
                return pstmt;
        }, holder);

        long contactInfoKey = holder.getKey().longValue();

        jdbcTemplate.update(connection -> {
                        PreparedStatement pstmt = connection.prepareStatement(
                                "INSERT INTO drone(make, model) VALUES(?, ?)",
                                new String[] {"id"});
                        pstmt.setString(1, accountDto.getDroneMake());
                        pstmt.setString(2, accountDto.getDroneModel());
                        return pstmt;
                }, holder);

        jdbcTemplate.update(connection -> {
                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO general_info(date_of_birth, place_of_birth, company_name, " +
                                "previous_flying_exp, preferred_location, drone_type_id) VALUES(?, ?, ?, ?, ?, ?)",
                        new String[] {"id"});
                pstmt.setObject(1, accountDto.getDob());
                pstmt.setString(2, accountDto.getPob());
                pstmt.setString(3, accountDto.getCompanyName());
                pstmt.setString(4, accountDto.getFlightExperience());
                pstmt.setString(5, accountDto.getPreferredLocation());
                pstmt.setLong(6, holder.getKey().longValue());
                return pstmt;
        }, holder);

        long generalInfoKey = holder.getKey().longValue();

        jdbcTemplate.update(connection -> {
                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO user(email, password, role) VALUES(?, ?, ?)",
                        new String[] {"id"});
                pstmt.setString(1, accountDto.getEmailAddress());
                pstmt.setString(2, passwordEncoder().encode(accountDto.getPassword()));
                pstmt.setString(3, "client");
                return pstmt;
        }, holder);

        long userIdKey = holder.getKey().longValue();

        jdbcTemplate.update(connection -> {
                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO candidate(user_id, first_name," +
                                "surname, contact_info_id, general_info_id) VALUES(?, ?, ?, ?, ?)",
                        new String[] {"reference_number"});
                pstmt.setLong(1, userIdKey);
                pstmt.setString(2, accountDto.getFirstName());
                pstmt.setString(3, accountDto.getLastName());
                pstmt.setLong(4, contactInfoKey);
                pstmt.setLong(5, generalInfoKey);
                return pstmt;
        });

    return 1;
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
