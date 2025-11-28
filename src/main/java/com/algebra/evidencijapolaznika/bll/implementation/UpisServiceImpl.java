package com.algebra.evidencijapolaznika.bll.implementation;

import com.algebra.evidencijapolaznika.bll.interfaces.UpisService;
import com.algebra.evidencijapolaznika.dal.entity.Upis;
import com.algebra.evidencijapolaznika.dal.repository.PolaznikRepository;
import com.algebra.evidencijapolaznika.dal.repository.ProgramObrazovanjaRepository;
import com.algebra.evidencijapolaznika.dal.repository.UpisRepository;
import com.algebra.evidencijapolaznika.dto.Request.UpisCreateDTO;
import com.algebra.evidencijapolaznika.dto.Response.UpisResponseDTO;
import com.algebra.evidencijapolaznika.mapper.UpisMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class UpisServiceImpl implements UpisService {

    private final UpisRepository upisRepository;
    private final PolaznikRepository polaznikRepository;
    private final ProgramObrazovanjaRepository programObrazovanjaRepository;

    @Override
    public Optional<UpisResponseDTO> findById(int id) {
        var rv = this.upisRepository.findById(id);
        return (rv.isPresent()) ? Optional.of(UpisMapper.createUpisResponseDTO(rv.get())) : Optional.empty();
    }

    @Override
    public List<UpisResponseDTO> findAll() {
        final List<UpisResponseDTO> dtoList = new ArrayList<>();
        var rv =  this.upisRepository.findAll();
        rv.forEach( upis -> dtoList.add(UpisMapper.createUpisResponseDTO(upis)));
        return dtoList;
    }

    @Override
    public Integer create(UpisCreateDTO dto) {
        var polaznikOpt = this.polaznikRepository.findById(dto.getPolaznikId());
        var programObrazovanjaOpt = this.programObrazovanjaRepository.findById(dto.getProgramObrazovanjaId());
        if( polaznikOpt.isPresent() && programObrazovanjaOpt.isPresent()) {
            try {
                Upis upis = this.upisRepository.save(new Upis(polaznikOpt.get(), programObrazovanjaOpt.get()));
                return upis.getId();
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
