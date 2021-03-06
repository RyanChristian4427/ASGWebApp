package com.assessment.asg.repositories.implementations;

import com.assessment.asg.domain.PasswordResetDto;
import com.assessment.asg.domain.User;
import com.assessment.asg.domain.AuthenticationToken;
import com.assessment.asg.domain.registration.UserRegistrationDto;
import com.assessment.asg.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<User> userMapper;
    private RowMapper<AuthenticationToken> verificationTokenMapper;

    @Autowired
    public UserRepositoryImpl(final JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        userMapper = (rs, i) -> new User(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getBoolean("activated"),
                rs.getBoolean("enabled")
        );

        verificationTokenMapper = (rs, i) -> new AuthenticationToken(
                rs.getString("email"),
                rs.getString("authentication_token"),
                rs.getTimestamp("expiry_datetime").toLocalDateTime()
        );
    }

    @Override
    public User findUserByEmail(final String emailAddress) {
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
    public void createAuthenticationToken(final AuthenticationToken authenticationToken) {
        jdbcTemplate.update("UPDATE user SET authentication_token = ?, expiry_datetime = ? WHERE email = ?",
                authenticationToken.getToken(), authenticationToken.getExpiryDate(), authenticationToken.getUserEmail());
    }

    @Override
    public AuthenticationToken getAuthenticationToken(final String token) {
        try {
            return jdbcTemplate.queryForObject("SELECT email, authentication_token, expiry_datetime FROM user " +
                    "WHERE authentication_token = ?", new Object[] {token}, verificationTokenMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void authenticateUser(final String userEmail) {
        jdbcTemplate.update("UPDATE user SET activated = 1 WHERE email = ?", userEmail);
    }

    @Override
    public void changePassword(final PasswordResetDto passwordResetDto) {
        jdbcTemplate.update("UPDATE user SET password = ? WHERE email = ?", passwordResetDto.getPassword(), passwordResetDto.getEmailAddress());
    }

    @Override
    public Integer saveUser(final UserRegistrationDto registrationDto) {
        return jdbcTemplate.update("INSERT INTO user(email, password, first_name, surname, role) VALUES(?, ?, ?, ?, ?)",
                registrationDto.getEmailAddress(), registrationDto.getPassword(), registrationDto.getFirstName(),
                registrationDto.getSurname(), "candidate");
    }

}
