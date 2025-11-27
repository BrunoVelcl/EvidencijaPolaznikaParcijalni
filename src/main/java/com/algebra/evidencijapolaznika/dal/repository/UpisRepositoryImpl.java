package com.algebra.evidencijapolaznika.dal.repository;

import com.algebra.evidencijapolaznika.dal.entity.Upis;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class UpisRepositoryImpl implements UpisRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Upis> findById(int id) {
        String sql = "SELECT * FROM upis";
        jdbcTemplate.queryForObject(sql, new Object[], )
    }

    @Override
    public List<Upis> findAll() {
        return List.of();
    }
}
