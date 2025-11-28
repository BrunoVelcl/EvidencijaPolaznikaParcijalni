package com.algebra.evidencijapolaznika.bll.interfaces;


import com.algebra.evidencijapolaznika.dto.Request.LoginRequestDTO;
import com.algebra.evidencijapolaznika.dto.Response.JwtResponseDTO;

import java.util.Optional;

public interface LoginService {

    Optional<JwtResponseDTO> login(LoginRequestDTO dto);
    Optional<JwtResponseDTO> refresh(String refreshToken);

}
