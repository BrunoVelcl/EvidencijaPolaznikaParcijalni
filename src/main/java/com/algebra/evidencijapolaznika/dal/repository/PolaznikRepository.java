package com.algebra.evidencijapolaznika.dal.repository;

import com.algebra.evidencijapolaznika.dal.entity.Polaznik;

import java.util.List;
import java.util.Optional;

public interface PolaznikRepository {
    Optional<Polaznik> findById(int id);
    List<Polaznik> findAll();
}
