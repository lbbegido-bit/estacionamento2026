package com.curso.estacionamento2026.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroVeiculoRequest(

        @NotBlank(message = "Placa é obrigatória")
        @Size(max = 10, message = "Placa deve possuir no máximo 10 caracteres")
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
        String cor
) {
}