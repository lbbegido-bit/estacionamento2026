package com.curso.estacionamento2026.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record VeiculoRequest(

        @NotBlank(message = "Placa é obrigatória")
        @Pattern(
                regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$",
                message = "Placa deve estar no formato ABC1D23"
        )
        String placa,

        @NotBlank(message = "Modelo é obrigatório")
        @Size(
                max = 150,
                message = "Modelo deve possuir no máximo 150 caracteres"
        )
        String modelo,

        @NotBlank(message = "Cor é obrigatória")
        @Size(
                max = 50,
                message = "Cor deve possuir no máximo 50 caracteres"
        )
        String cor,

        @NotNull(message = "Tipo de veículo é obrigatório")
        @Positive(
                message = "Identificador do tipo de veículo deve ser positivo"
        )
        Long tipoVeiculoId
) {
}