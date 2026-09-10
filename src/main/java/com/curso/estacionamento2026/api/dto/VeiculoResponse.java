package com.curso.estacionamento2026.api.dto;

public record VeiculoResponse(
        Long id,
        String placa,
        String modelo,
        String cor,
        Long tipoVeiculoId
) {
}