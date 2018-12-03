package com.assessment.drones.repository.implementations;

import com.assessment.drones.domain.User;
import com.assessment.drones.domain.VerificationToken;
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
    private RowMapper<VerificationToken> verificationTokenMapper;

    @Autowired
    public UserRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        userMapper = (rs, i) -> new User(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getBoolean("activated"),
                rs.getBoolean("enabled")
        );

        verificationTokenMapper = (rs, i) -> new VerificationToken(
                rs.getString("email"),
                rs.getString("authentication_token"),
                rs.getTimestamp("expiry_datetime").toLocalDateTime()
        );
    }

    @Override
    public User findUserByEmail(String emailAddress) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT email, password, role, activated, enabled FROM user WHERE user.email = ?",
                    new Object[]{emailAddress},
                    userMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void createVerificationToken(VerificationToken verificationToken){
        jdbcTemplate.update("UPDATE user SET authentication_token = ?, expiry_datetime = ? WHERE email = ?",
                verificationToken.getToken(), verificationToken.getExpiryDate(), verificationToken.getUserEmail());
    }

    @Override
    public VerificationToken getVerificationToken(String token){
        return jdbcTemplate.queryForObject("SELECT email, authentication_token, expiry_datetime FROM user " +
                "WHERE authentication_token = ?", new Object[] {token}, verificationTokenMapper);

    }

    @Override
    public void authenticateUser(String userEmail){
        jdbcTemplate.update("UPDATE user SET activated = 1 WHERE email = ?", userEmail);
    }

}
