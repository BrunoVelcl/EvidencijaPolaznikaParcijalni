package com.algebra.evidencijapolaznika.dal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Upis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Polaznik polaznik;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProgramObrazovanja programObrazovanja;
}
