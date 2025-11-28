package com.algebra.evidencijapolaznika.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpisCreateDTO {

    @NotNull
    @Positive
    private int polaznikId;

    @NotNull
    @Positive
    private int programObrazovanjaId;

}
