package com.algebra.evidencijapolaznika.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PolaznikCreateRequestDTO {

    @NotBlank
    private String ime;

    @NotBlank
    private String prezime;
}
