package com.algebra.evidencijapolaznika.controller;

import com.algebra.evidencijapolaznika.bll.interfaces.LoginService;
import com.algebra.evidencijapolaznika.bll.interfaces.UserAccountService;
import com.algebra.evidencijapolaznika.dto.Request.LoginRequestDTO;
import com.algebra.evidencijapolaznika.dto.Request.UserAccountCreateRequestDTO;
import com.algebra.evidencijapolaznika.dto.Response.JwtResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userAccountService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<Void> create(@Valid @RequestBody UserAccountCreateRequestDTO dto){
        return (this.userAccountService.create(dto)) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto){
        var rv = this.loginService.login(dto);
        return (rv.isPresent()) ? ResponseEntity.ok(rv.get()) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDTO> refresh(@RequestBody Map<String, String> body){
        var rv = this.loginService.refresh(body.get("refreshToken"));
        return (rv.isPresent()) ? ResponseEntity.ok(rv.get()) : ResponseEntity.badRequest().build();
    }
}
