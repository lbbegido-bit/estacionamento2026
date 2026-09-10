package com.curso.estacionamento2026.api.mapper;

import com.curso.estacionamento2026.api.dto.CadastroVeiculoRequest;
import com.curso.estacionamento2026.api.dto.CadastroVeiculoResponse;
import com.curso.estacionamento2026.domain.Veiculo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CadastroVeiculoMapper {

    public Veiculo toEntity(CadastroVeiculoRequest request) {
        return new Veiculo(
                request.placa(),
                request.modelo(),
                request.cor(),
                LocalDateTime.now()
        );
    }

    public CadastroVeiculoResponse toResponse(Veiculo veiculo) {
        return new CadastroVeiculoResponse(
                veiculo.getId(),
                veiculo.getPlaca(),
                veiculo.getModelo(),
                veiculo.getCor(),
                veiculo.getDataCadastro(),
                veiculo.getStatus(),
                veiculo.getTipoVeiculo().getId(),
                veiculo.getTipoVeiculo().getNome()
        );
    }
}