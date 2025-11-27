package com.algebra.evidencijapolaznika.mapper;


import com.algebra.evidencijapolaznika.dal.entity.Polaznik;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PolaznikRowMapper implements RowMapper<Polaznik> {

    @Override
    public Polaznik mapRow(ResultSet rs, int rowNum) throws SQLException {
        Polaznik polaznik = new Polaznik();
        polaznik.setId(rs.getInt("id"));
        polaznik.setIme(rs.getString("ime"));
        polaznik.setPrezime(rs.getString("prezime"));
        return polaznik;
    }
}
