package com.algebra.evidencijapolaznika.bll.interfaces;

import com.algebra.evidencijapolaznika.dal.entity.Upis;
import com.algebra.evidencijapolaznika.dto.Request.UpisCreateDTO;
import com.algebra.evidencijapolaznika.dto.Response.UpisResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UpisService {
    Optional<UpisResponseDTO> findById(int id);
    List<UpisResponseDTO> findAll();
    Integer create(UpisCreateDTO dto);
}
