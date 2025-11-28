package com.algebra.evidencijapolaznika.bll.interfaces;

import io.jsonwebtoken.Claims;

public interface JwtService {
    Claims extractClaims(String token);
    String createToken(String username);
}
