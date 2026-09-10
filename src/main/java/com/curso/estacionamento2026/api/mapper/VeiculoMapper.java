package com.curso.estacionamento2026.api.mapper;

import com.curso.estacionamento2026.api.dto.VeiculoRequest;
import com.curso.estacionamento2026.api.dto.VeiculoResponse;
import com.curso.estacionamento2026.domain.Veiculo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VeiculoMapper {

    public Veiculo toEntity(VeiculoRequest request) {
        return new Veiculo(
                request.placa(),
                request.modelo(),
                request.cor(),
                LocalDateTime.now()
        );
    }

    public VeiculoResponse toResponse(Veiculo veiculo) {

        Long tipoVeiculoId = null;

        if (veiculo.getTipoVeiculo() != null) {
            tipoVeiculoId = veiculo.getTipoVeiculo().getId();
        }

        return new VeiculoResponse(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                tipoVeiculoId
        );
    }
}