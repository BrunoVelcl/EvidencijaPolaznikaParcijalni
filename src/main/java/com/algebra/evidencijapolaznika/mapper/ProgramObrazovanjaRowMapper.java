package com.algebra.evidencijapolaznika.mapper;

import com.algebra.evidencijapolaznika.dal.entity.ProgramObrazovanja;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramObrazovanjaRowMapper implements RowMapper<ProgramObrazovanja> {

    @Override
    public ProgramObrazovanja mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProgramObrazovanja programObrazovanja = new ProgramObrazovanja();
        programObrazovanja.setId(rs.getInt("id"));
        programObrazovanja.setNaziv(rs.getString("naziv"));
        programObrazovanja.setCsvet(rs.getInt("csvet"));
        return programObrazovanja;
    }
}
