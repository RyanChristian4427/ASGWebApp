package com.assessment.drones.repository;

import com.assessment.drones.domain.GeneralInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GeneralRepositoryJdbc implements GeneralRepository
{
    private JdbcTemplate jdbcTemplate;
    private RowMapper<GeneralInfo> reviewMapper;

    @Autowired
    public GeneralRepositoryJdbc(JdbcTemplate aTemplate){
        jdbcTemplate = aTemplate;

        reviewMapper = (rs, i) -> new GeneralInfo(
                rs.getLong("id"),
                rs.getString("firstName"),
                rs.getString("surname"),
                rs.getString("dob"),
                rs.getString("placeOfBirth"),
                rs.getLong("addressID"),
                rs.getString("companyName"),
                rs.getString("prevFlyingExp"),
                rs.getString("location"),
                rs.getString("droneType"),
                rs.getString("candidateNumber")
                );
    }

    @Override
    public Integer addGeneral(GeneralInfo general){
        ArrayList<Object> params = new ArrayList<>();
        params.add(general.getFirstName());
        params.add(general.getSurname());
        params.add(general.getDob());
        params.add(general.getPlaceOfBirth());
        params.add(general.getAddressID());
        params.add(general.getCompanyName());
        params.add(general.getPrevFlyingExp());
        params.add(general.getLocation());
        params.add(general.getDroneType());
        params.add(general.getCandidateNumber());
        return jdbcTemplate.update(
                "INSERT INTO general(first_name, surname, date_of_birth, place_of_birth, address_id, company_name, previous_flying_exp, preferred_location, drone_type, candidate_number) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                params.toArray());
    }

}
