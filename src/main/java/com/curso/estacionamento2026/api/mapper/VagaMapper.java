package com.curso.estacionamento2026.api.mapper;

import com.curso.estacionamento2026.api.dto.VagaRequest;
import com.curso.estacionamento2026.api.dto.VagaResponse;
import com.curso.estacionamento2026.domain.Vaga;
import org.springframework.stereotype.Component;

@Component
public class VagaMapper {

    public Vaga toEntity(VagaRequest request) {
        return new Vaga(
                request.numero(),
                request.localizacao()
        );
    }

    public VagaResponse toResponse(Vaga vaga) {
        return new VagaResponse(
                vaga.getId(),
                vaga.getNumero(),
                vaga.getLocalizacao(),
                vaga.getStatus()
        );
    }
}