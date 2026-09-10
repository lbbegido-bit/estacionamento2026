package com.curso.estacionamento2026.api.mapper;

import com.curso.estacionamento2026.api.dto.TipoVeiculoRequest;
import com.curso.estacionamento2026.api.dto.TipoVeiculoResponse;
import com.curso.estacionamento2026.domain.TipoVeiculo;
import org.springframework.stereotype.Component;

@Component
public class TipoVeiculoMapper {

    public TipoVeiculo toEntity(TipoVeiculoRequest request) {
        return new TipoVeiculo(request.nome());
    }

    public TipoVeiculoResponse toResponse(TipoVeiculo tipoVeiculo) {
        return new TipoVeiculoResponse(
                tipoVeiculo.getId(),
                tipoVeiculo.getNome(),
                tipoVeiculo.getStatus()
        );
    }
}