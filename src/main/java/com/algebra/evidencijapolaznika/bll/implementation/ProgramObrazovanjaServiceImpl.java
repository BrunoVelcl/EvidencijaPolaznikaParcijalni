package com.algebra.evidencijapolaznika.bll.implementation;

import com.algebra.evidencijapolaznika.bll.interfaces.ProgramObrazovanjaService;
import com.algebra.evidencijapolaznika.dal.entity.ProgramObrazovanja;
import com.algebra.evidencijapolaznika.dal.repository.ProgramObrazovanjaRepository;
import com.algebra.evidencijapolaznika.dto.Request.ProgramObrazovanjaCreateRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProgramObrazovanjaServiceImpl implements ProgramObrazovanjaService {

    private final ProgramObrazovanjaRepository programObrazovanjaRepository;

    @Override
    public Optional<ProgramObrazovanja> findById(int id) {
        return this.programObrazovanjaRepository.findById(id);
    }

    @Override
    public List<ProgramObrazovanja> findAll() {
        return this.programObrazovanjaRepository.findAll();
    }

    @Override
    public Integer create(ProgramObrazovanjaCreateRequestDTO dto) {
        try {
            ProgramObrazovanja programObrazovanja = this.programObrazovanjaRepository.save(new ProgramObrazovanja(dto.getNaziv(), dto.getCsvet()));
            return programObrazovanja.getId();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;

    }
}
