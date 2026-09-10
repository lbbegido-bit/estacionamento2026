package com.curso.estacionamento2026.api.controller;

import com.curso.estacionamento2026.api.dto.TipoVeiculoRequest;
import com.curso.estacionamento2026.api.dto.TipoVeiculoResponse;
import com.curso.estacionamento2026.api.mapper.TipoVeiculoMapper;
import com.curso.estacionamento2026.application.TipoVeiculoService;
import com.curso.estacionamento2026.domain.TipoVeiculo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-veiculos")
public class TipoVeiculoController {

    private final TipoVeiculoService service;
    private final TipoVeiculoMapper mapper;

    public TipoVeiculoController(
            TipoVeiculoService service,
            TipoVeiculoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<TipoVeiculoResponse> cadastrar(
            @Valid @RequestBody TipoVeiculoRequest request) {

        TipoVeiculo tipoVeiculo = service.cadastrar(request.nome());

        URI location = URI.create(
                "/api/tipos-veiculos/" + tipoVeiculo.getId()
        );

        return ResponseEntity
                .created(location)
                .body(mapper.toResponse(tipoVeiculo));
    }

    @GetMapping("/{id}")
    public TipoVeiculoResponse buscarPorId(@PathVariable Long id) {
        return mapper.toResponse(service.buscarPorId(id));
    }

    @GetMapping
    public List<TipoVeiculoResponse> listar() {
        return service.listar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}