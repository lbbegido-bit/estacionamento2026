package com.curso.estacionamento2026.application;

import com.curso.estacionamento2026.domain.TipoVeiculo;
import com.curso.estacionamento2026.domain.Veiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {

    private final List<Veiculo> veiculos = new ArrayList<>();
    private final TipoVeiculoService tipoVeiculoService;

    private Long proximoId = 1L;

    public VeiculoService(TipoVeiculoService tipoVeiculoService) {
        this.tipoVeiculoService = tipoVeiculoService;
    }

    public Veiculo cadastrar(Veiculo veiculo, Long tipoVeiculoId) {

        TipoVeiculo tipoVeiculo =
                tipoVeiculoService.buscarPorId(tipoVeiculoId);

        veiculo.setId(proximoId++);
        veiculo.setTipoVeiculo(tipoVeiculo);

        veiculos.add(veiculo);

        return veiculo;
    }

    public Veiculo buscarPorId(Long id) {

        return veiculos.stream()
                .filter(veiculo -> veiculo.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Veículo não encontrado"
                        )
                );
    }

    public List<Veiculo> listar() {
        return new ArrayList<>(veiculos);
    }
}