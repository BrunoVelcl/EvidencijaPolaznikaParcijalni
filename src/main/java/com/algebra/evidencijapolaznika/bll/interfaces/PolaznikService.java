package com.algebra.evidencijapolaznika.bll.interfaces;

import com.algebra.evidencijapolaznika.dal.entity.Polaznik;
import com.algebra.evidencijapolaznika.dto.Request.PolaznikCreateRequestDTO;

import java.util.List;
import java.util.Optional;


public interface PolaznikService {

    Optional<Polaznik> findById(int id);
    List<Polaznik> findAll();
    Integer create(PolaznikCreateRequestDTO dto);
}
