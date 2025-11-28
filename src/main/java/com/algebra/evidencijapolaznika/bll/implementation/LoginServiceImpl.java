package com.algebra.evidencijapolaznika.bll.implementation;

import com.algebra.evidencijapolaznika.bll.interfaces.JwtService;
import com.algebra.evidencijapolaznika.bll.interfaces.LoginService;
import com.algebra.evidencijapolaznika.bll.interfaces.RefreshTokenService;
import com.algebra.evidencijapolaznika.bll.interfaces.UserAccountService;
import com.algebra.evidencijapolaznika.component.PwEncoder;
import com.algebra.evidencijapolaznika.dal.entity.RefreshToken;
import com.algebra.evidencijapolaznika.dal.entity.UserAccount;
import com.algebra.evidencijapolaznika.dto.Request.LoginRequestDTO;
import com.algebra.evidencijapolaznika.dto.Response.JwtResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final PwEncoder pwEncoder;
    private final UserAccountService userAccountService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public Optional<JwtResponseDTO> login(LoginRequestDTO dto) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        if(authentication.isAuthenticated()) {
            var accountOpt = this.userAccountService.findByUsername(dto.getUsername());
            if (accountOpt.isEmpty()) return Optional.empty();
            UserAccount userAccount = accountOpt.get();
            if (this.pwEncoder.getEncoder().matches(dto.getPassword(), userAccount.getPassword())) {
                String accessToken = jwtService.createToken(userAccount.getUsername());
                String refreshToken = this.refreshTokenService.create(userAccount);
                return Optional.of(new JwtResponseDTO(accessToken, refreshToken));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<JwtResponseDTO> refresh(String refreshToken) {
        var tokenOpt = this.refreshTokenService.findByToken(refreshToken);
        if(tokenOpt.isPresent()){
            RefreshToken token = tokenOpt.get();
            if( token.getExpiry().isAfter(Instant.now())){
                String accessToken = this.jwtService.createToken(token.getUserAccount().getUsername());
                return Optional.of(new JwtResponseDTO(accessToken, refreshToken));
            }
        }
        return Optional.empty();
    }
}
