package com.algebra.evidencijapolaznika.component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PwEncoder {

    private final PasswordEncoder encoder;

    public PwEncoder() {
        this.encoder = new BCryptPasswordEncoder();
    }

    public PasswordEncoder getEncoder(){
        return this.encoder;
    }
}
