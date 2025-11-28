package com.algebra.evidencijapolaznika.bll.interfaces;

import com.algebra.evidencijapolaznika.dal.entity.RefreshToken;
import com.algebra.evidencijapolaznika.dal.entity.UserAccount;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    String create(UserAccount userAccount);
    void delete(RefreshToken refreshToken);
}
