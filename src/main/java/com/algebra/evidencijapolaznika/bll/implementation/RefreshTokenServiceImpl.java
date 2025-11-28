package com.algebra.evidencijapolaznika.bll.implementation;

import com.algebra.evidencijapolaznika.bll.interfaces.RefreshTokenService;
import com.algebra.evidencijapolaznika.dal.entity.RefreshToken;
import com.algebra.evidencijapolaznika.dal.entity.UserAccount;
import com.algebra.evidencijapolaznika.dal.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return this.refreshTokenRepository.findByToken(token);
    }

    @Override
    public String create(UserAccount userAccount) {
        String refreshToken = UUID.randomUUID().toString();
        Instant expiry = Instant.now().plusSeconds(6000000);
        this.refreshTokenRepository.save(new RefreshToken(refreshToken, expiry, userAccount));
        return refreshToken;
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        this.refreshTokenRepository.delete(refreshToken);
    }


}
