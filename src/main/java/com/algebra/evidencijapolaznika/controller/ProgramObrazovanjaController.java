package com.algebra.evidencijapolaznika.controller;


import com.algebra.evidencijapolaznika.bll.interfaces.ProgramObrazovanjaService;
import com.algebra.evidencijapolaznika.dal.entity.ProgramObrazovanja;
import com.algebra.evidencijapolaznika.dto.Request.ProgramObrazovanjaCreateRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/program")
public class ProgramObrazovanjaController {

    private final ProgramObrazovanjaService programObrazovanjaService;

    @GetMapping
    public ResponseEntity<List<ProgramObrazovanja>> findAll(){
        var rv = this.programObrazovanjaService.findAll();
        return (rv.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(rv);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramObrazovanja> findById(@PathVariable int id){
        var rv = this.programObrazovanjaService.findById(id);
        return (rv.isPresent()) ? ResponseEntity.ok(rv.get()) : ResponseEntity.noContent().build();
    }

    //U zadatku se navodi samo implementacija get metoda, ali se u daljnim zahtjevima spominju...
    //"validacije koje će osigurati da se mogu spremati samo isparvi podaci"

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ProgramObrazovanjaCreateRequestDTO dto){
        Integer rv = this.programObrazovanjaService.create(dto);
        return (rv != null) ? ResponseEntity.created(URI.create("/api/program/" + rv)).build() : ResponseEntity.badRequest().build();
    }
}
