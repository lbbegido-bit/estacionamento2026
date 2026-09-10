package com.curso.estacionamento2026.api.dto;

import com.curso.estacionamento2026.domain.Status;

import java.time.LocalDateTime;

public record CadastroVeiculoResponse(
        Long id,
        String placa,
        String modelo,
        String cor,
        LocalDateTime dataCadastro,
        Status status,
        Long tipoVeiculoId,
        String tipoVeiculoNome
) {
}