package com.algebra.evidencijapolaznika.dal.repository;

import com.algebra.evidencijapolaznika.dal.entity.ProgramObrazovanja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramObrazovanjaRepository extends JpaRepository<ProgramObrazovanja, Integer> {

}
