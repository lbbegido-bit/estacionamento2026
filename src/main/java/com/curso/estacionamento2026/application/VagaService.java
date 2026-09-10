package com.curso.estacionamento2026.application;

import com.curso.estacionamento2026.domain.Vaga;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VagaService {

    private final List<Vaga> vagas = new ArrayList<>();

    private Long proximoId = 1L;

    public Vaga cadastrar(Vaga vaga) {

        vaga.setId(proximoId++);

        vagas.add(vaga);

        return vaga;
    }

    public Vaga buscarPorId(Long id) {

        return vagas.stream()
                .filter(vaga -> vaga.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Vaga não encontrada"
                        )
                );
    }

    public List<Vaga> listar() {
        return new ArrayList<>(vagas);
    }
}