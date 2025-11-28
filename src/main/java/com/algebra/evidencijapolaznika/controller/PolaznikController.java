package com.algebra.evidencijapolaznika.controller;

import com.algebra.evidencijapolaznika.bll.interfaces.PolaznikService;
import com.algebra.evidencijapolaznika.dal.entity.Polaznik;
import com.algebra.evidencijapolaznika.dto.Request.PolaznikCreateRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/polaznik")
public class PolaznikController {

    private final PolaznikService polaznikService;

    @GetMapping()
    public ResponseEntity<List<Polaznik>> findAll(){
        var rv = this.polaznikService.findAll();
        return (rv.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(rv);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Polaznik> findById(@PathVariable int id){
        var rv = this.polaznikService.findById(id);
        return (rv.isPresent()) ? ResponseEntity.ok(rv.get()) : ResponseEntity.noContent().build();
    }

    //U zadatku se navodi samo implementacija get metoda, ali se u daljnim zahtjevima spominju...
    //"validacije koje će osigurati da se mogu spremati samo isparvi podaci"

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody PolaznikCreateRequestDTO dto){
        Integer rv = this.polaznikService.create(dto);
        return (rv != null) ? ResponseEntity.created(URI.create("/api/polaznik/" + rv)).build() : ResponseEntity.badRequest().build();
    }
}
