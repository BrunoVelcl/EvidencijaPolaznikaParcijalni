package com.algebra.evidencijapolaznika.bll.implementation;

import com.algebra.evidencijapolaznika.bll.interfaces.PolaznikService;
import com.algebra.evidencijapolaznika.dal.entity.Polaznik;
import com.algebra.evidencijapolaznika.dal.repository.PolaznikRepository;
import com.algebra.evidencijapolaznika.dto.Request.PolaznikCreateRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PolaznikServiceImpl implements PolaznikService {

    private final PolaznikRepository polaznikRepository;

    @Override
    public Optional<Polaznik> findById(int id) {
        return this.polaznikRepository.findById(id);
    }

    @Override
    public List<Polaznik> findAll() {
        return this.polaznikRepository.findAll();
    }

    @Override
    public Integer create(PolaznikCreateRequestDTO dto) {
        try {
            Polaznik polaznik = this.polaznikRepository.save(new Polaznik(dto.getIme(), dto.getPrezime()));
            return polaznik.getId();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
