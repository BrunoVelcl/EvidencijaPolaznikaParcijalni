package com.algebra.evidencijapolaznika.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProgramObrazovanjaCreateRequestDTO {

    @NotBlank
    private String naziv;

    @NotNull
    @PositiveOrZero
    private Integer csvet;
}
