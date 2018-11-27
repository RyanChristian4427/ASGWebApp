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
    private RowMapper<GeneralInfo> generalInfoRowMapper;

    @Autowired
    public GeneralRepositoryJdbc(JdbcTemplate aTemplate){
        jdbcTemplate = aTemplate;

        generalInfoRowMapper = (rs, i) -> new GeneralInfo(
                rs.getLong("id"),
                rs.getString("dob"),
                rs.getString("placeOfBirth"),
                rs.getString("companyName"),
                rs.getString("prevFlyingExp"),
                rs.getLong("droneTypeId")
                );
    }

    @Override
    public Integer addGeneral(GeneralInfo general){
        ArrayList<Object> params = new ArrayList<>();
        params.add(general.getDob());
        params.add(general.getPlaceOfBirth());
        params.add(general.getCompanyName());
        params.add(general.getPrevFlyingExp());
        params.add(general.getDroneTypeId());
        return jdbcTemplate.update(
                "INSERT INTO general(date_of_birth, place_of_birth, company_name, previous_flying_exp, drone_type_id) " +
                        "VALUES(?, ?, ?, ?, ?)",
                params.toArray());
    }

}
