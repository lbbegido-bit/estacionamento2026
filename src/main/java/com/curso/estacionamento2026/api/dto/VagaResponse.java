package com.curso.estacionamento2026.api.dto;

import com.curso.estacionamento2026.domain.Status;

public record VagaResponse(
        Long id,
        String numero,
        String localizacao,
        Status status
) {
}