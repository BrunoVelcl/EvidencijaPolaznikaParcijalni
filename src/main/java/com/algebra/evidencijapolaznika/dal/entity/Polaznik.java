package com.algebra.evidencijapolaznika.dal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Polaznik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String ime;

    @Column(nullable = false)
    private String prezime;
}
