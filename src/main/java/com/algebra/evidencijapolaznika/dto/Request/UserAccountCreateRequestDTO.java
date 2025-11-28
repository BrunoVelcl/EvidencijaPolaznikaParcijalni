package com.algebra.evidencijapolaznika.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAccountCreateRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
