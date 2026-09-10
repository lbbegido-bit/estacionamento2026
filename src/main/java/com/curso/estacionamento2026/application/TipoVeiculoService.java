package com.curso.estacionamento2026.application;

import com.curso.estacionamento2026.domain.TipoVeiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoVeiculoService {

    private final List<TipoVeiculo> tiposVeiculos = new ArrayList<>();
    private Long proximoId = 1L;

    public TipoVeiculo cadastrar(String nome) {

        TipoVeiculo tipoVeiculo = new TipoVeiculo(nome);

        tipoVeiculo.setId(proximoId++);

        tiposVeiculos.add(tipoVeiculo);

        return tipoVeiculo;
    }

    public TipoVeiculo buscarPorId(Long id) {

        return tiposVeiculos.stream()
                .filter(tipoVeiculo -> tipoVeiculo.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Tipo de veículo não encontrado"
                        )
                );
    }

    public List<TipoVeiculo> listar() {
        return new ArrayList<>(tiposVeiculos);
    }
}