package com.algebra.evidencijapolaznika.mapper;

import com.algebra.evidencijapolaznika.dal.entity.Upis;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpisRowMapper implements RowMapper<Upis> {

    @Override
    public Upis mapRow(ResultSet rs, int rowNum) throws SQLException {
        Upis upis = new Upis();
        upis.setId(rs.getInt("id"));
        upis.setPolaznik(rs.getInt("polaznikID"));
    }
}
