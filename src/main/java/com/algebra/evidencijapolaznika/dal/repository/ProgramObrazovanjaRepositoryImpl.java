package com.algebra.evidencijapolaznika.dal.repository;

import com.algebra.evidencijapolaznika.dal.entity.ProgramObrazovanja;
import com.algebra.evidencijapolaznika.mapper.ProgramObrazovanjaRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ProgramObrazovanjaRepositoryImpl implements ProgramObrazovanjaRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<ProgramObrazovanja> findById(int id) {
        String sql = "SELECT * FROM programObrazovanja WHERE id = ?";
        ProgramObrazovanja programObrazovanja = this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProgramObrazovanjaRowMapper());
        return (programObrazovanja != null) ? Optional.of(programObrazovanja) : Optional.empty();
    }

    @Override
    public List<ProgramObrazovanja> findAll() {
        String sql = "SELECT * FROM programObrazovanja";
        return this.jdbcTemplate.query(sql, new ProgramObrazovanjaRowMapper());
    }
}

