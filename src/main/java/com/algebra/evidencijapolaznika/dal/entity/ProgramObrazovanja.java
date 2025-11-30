package com.algebra.evidencijapolaznika.dal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProgramObrazovanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @Column(nullable = false)
    private Integer csvet;

    public ProgramObrazovanja(String naziv, Integer csvet) {
        this.naziv = naziv;
        this.csvet = csvet;
    }
}
