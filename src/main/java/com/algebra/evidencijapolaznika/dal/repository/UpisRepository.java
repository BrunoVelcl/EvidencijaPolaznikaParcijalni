package com.algebra.evidencijapolaznika.dal.repository;

import com.algebra.evidencijapolaznika.dal.entity.Upis;

import java.util.List;
import java.util.Optional;

public interface UpisRepository {
    Optional<Upis> findById(int id);
    List<Upis> findAll();
}
