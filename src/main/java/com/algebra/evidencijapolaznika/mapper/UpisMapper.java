package com.algebra.evidencijapolaznika.mapper;

import com.algebra.evidencijapolaznika.dal.entity.Upis;
import com.algebra.evidencijapolaznika.dto.Response.UpisResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UpisMapper {
    public static UpisResponseDTO createUpisResponseDTO(Upis upis){
        UpisResponseDTO dto = new UpisResponseDTO();
        dto.setId(upis.getId());
        dto.setIme(upis.getPolaznik().getIme());
        dto.setPrezime(upis.getPolaznik().getPrezime());
        dto.setProgram(upis.getProgramObrazovanja().getNaziv());
        dto.setCsvet(upis.getProgramObrazovanja().getCsvet());
        return dto;
    };
}
