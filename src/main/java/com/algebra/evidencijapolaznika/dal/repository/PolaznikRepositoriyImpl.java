package com.algebra.evidencijapolaznika.dal.repository;

import com.algebra.evidencijapolaznika.dal.entity.Polaznik;
import com.algebra.evidencijapolaznika.mapper.PolaznikRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class PolaznikRepositoriyImpl implements PolaznikRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Polaznik> findById(int id) {
        String sql = "SELECT * FROM polaznik WHERE id = ?";
        Polaznik polaznik = this.jdbcTemplate.queryForObject(sql, new Object[]{id}, new PolaznikRowMapper());
        return (polaznik != null) ? Optional.of(polaznik) : Optional.empty();
    }

    @Override
    public List<Polaznik> findAll() {
        String sql = "SELECT * FROM polaznik";
        return this.jdbcTemplate.query(sql, new PolaznikRowMapper());
    }
}
