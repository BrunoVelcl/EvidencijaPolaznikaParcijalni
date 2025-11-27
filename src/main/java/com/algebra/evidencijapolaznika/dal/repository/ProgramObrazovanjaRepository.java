package com.algebra.evidencijapolaznika.dal.repository;

import com.algebra.evidencijapolaznika.dal.entity.ProgramObrazovanja;

import java.util.List;
import java.util.Optional;

public interface ProgramObrazovanjaRepository {
    Optional<ProgramObrazovanja> findById(int id);
    List<ProgramObrazovanja> findAll();
}
