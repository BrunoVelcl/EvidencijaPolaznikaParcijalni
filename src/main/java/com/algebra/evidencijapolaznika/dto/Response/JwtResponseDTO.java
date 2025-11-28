package com.algebra.evidencijapolaznika.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
}
