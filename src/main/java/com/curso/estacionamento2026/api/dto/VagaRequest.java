package com.curso.estacionamento2026.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VagaRequest(

        @NotBlank(message = "Número da vaga é obrigatório")
        @Size(max = 20, message = "Número da vaga deve possuir no máximo 20 caracteres")
        String numero,

        @NotBlank(message = "Localização é obrigatória")
        @Size(max = 100, message = "Localização deve possuir no máximo 100 caracteres")
        String localizacao
) {
}