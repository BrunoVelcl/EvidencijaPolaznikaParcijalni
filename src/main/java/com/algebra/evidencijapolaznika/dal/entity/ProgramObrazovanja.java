package com.algebra.evidencijapolaznika.dal.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "programObrazovanja")
public class ProgramObrazovanja {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String naziv;

    @Column(nullable = false)
    private Integer csvet;
}
