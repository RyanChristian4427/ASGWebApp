package com.assessment.drones.repository.implementations;

import com.assessment.drones.domain.Candidate;
import com.assessment.drones.domain.RegistrationDto;
import com.assessment.drones.repository.interfaces.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CandidateRepositoryJdbc implements CandidateRepository {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Candidate> candidateRowMapper;

    @Autowired
    public CandidateRepositoryJdbc(JdbcTemplate aTemplate) {
        jdbcTemplate = aTemplate;

        candidateRowMapper = (rs, i) -> new Candidate(
                rs.getString("reference_number"),
                rs.getString("first_name"),
                rs.getString("surname")
        );
    }

    @Override
    public String previousCandidateReferenceNumber() {
        return jdbcTemplate.queryForObject(
                "SELECT reference_number FROM candidate WHERE id=(SELECT max(id) FROM candidate)", String.class);
    }

    @Override
    public Optional<Candidate> findCandidateByNumber(String candidateNumber) {
        try {
            return Optional.of(
                    jdbcTemplate.queryForObject(
                            "SELECT * FROM candidate WHERE reference_number = ?",
                            new Object[]{candidateNumber},
                            candidateRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Integer saveUser(RegistrationDto accountDto, String newReferenceNumber) {
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
                            "previous_flying_exp, preferred_location, drone_type_id, english_speaking, disability) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                    new String[] {"id"});
            pstmt.setObject(1, accountDto.getDob());
            pstmt.setString(2, accountDto.getPob());
            pstmt.setString(3, accountDto.getCompanyName());
            pstmt.setString(4, accountDto.getFlightExperience());
            pstmt.setString(5, accountDto.getPreferredLocation());
            pstmt.setLong(6, holder.getKey().longValue());
            pstmt.setLong(7, accountDto.getEnglishLevel());
            pstmt.setString(8, accountDto.getDisability());
            return pstmt;
        }, holder);

        long generalInfoKey = holder.getKey().longValue();

        jdbcTemplate.update("INSERT INTO user(email, password, role) VALUES(?, ?, ?)",
                    accountDto.getEmailAddress(), accountDto.getPassword(), "candidate");

        jdbcTemplate.update("INSERT INTO candidate(reference_number, user_id, first_name," +
                            "surname, contact_info_id, general_info_id) VALUES(?, ?, ?, ?, ?, ?)",
                newReferenceNumber, accountDto.getEmailAddress(), accountDto.getFirstName(),
                accountDto.getLastName(), contactInfoKey, generalInfoKey);

        return 1;
    }
}
