package com.curso.estacionamento2026.api.dto;

import com.curso.estacionamento2026.domain.Status;

public record TipoVeiculoResponse(
        Long id,
        String nome,
        Status status
) {
}