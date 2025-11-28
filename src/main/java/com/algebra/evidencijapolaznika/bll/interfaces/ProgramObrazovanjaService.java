package com.algebra.evidencijapolaznika.bll.interfaces;

import com.algebra.evidencijapolaznika.dal.entity.ProgramObrazovanja;
import com.algebra.evidencijapolaznika.dto.Request.ProgramObrazovanjaCreateRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ProgramObrazovanjaService {
    Optional<ProgramObrazovanja> findById(int id);
    List<ProgramObrazovanja> findAll();
    Integer create (ProgramObrazovanjaCreateRequestDTO dto);
}
