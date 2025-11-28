package com.algebra.evidencijapolaznika.controller;

import com.algebra.evidencijapolaznika.bll.interfaces.UpisService;
import com.algebra.evidencijapolaznika.dto.Request.UpisCreateDTO;
import com.algebra.evidencijapolaznika.dto.Response.UpisResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/upis")
public class UpisController {

    private final UpisService upisService;

    @GetMapping
    public ResponseEntity<List<UpisResponseDTO>> findAll(){
        var rv = this.upisService.findAll();
        return (rv.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(rv);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpisResponseDTO> findById(@PathVariable int id){
        var rv = this.upisService.findById(id);
        return (rv.isPresent()) ? ResponseEntity.ok(rv.get()) : ResponseEntity.noContent().build();
    }

    //U zadatku se navodi samo implementacija get metoda, ali se u daljnim zahtjevima spominju...
    //"validacije koje će osigurati da se mogu spremati samo isparvi podaci"

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UpisCreateDTO dto){
        Integer rv  = this.upisService.create(dto);
        return (rv != null) ? ResponseEntity.created(URI.create("/api/upis/" + rv)).build() : ResponseEntity.badRequest().build();
    }
}
