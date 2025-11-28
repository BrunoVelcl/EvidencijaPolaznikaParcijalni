package com.algebra.evidencijapolaznika.dal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Instant expiry;

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private UserAccount userAccount;

    public RefreshToken(String token, Instant expiry, UserAccount userAccount) {
        this.token = token;
        this.expiry = expiry;
        this.userAccount = userAccount;
    }
}
