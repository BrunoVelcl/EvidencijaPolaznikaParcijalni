package com.algebra.evidencijapolaznika.dal.repository;

import com.algebra.evidencijapolaznika.dal.entity.Polaznik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolaznikRepository extends JpaRepository<Polaznik, Integer> {

}
