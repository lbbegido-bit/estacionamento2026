package com.curso.estacionamento2026.api.controller;

import com.curso.estacionamento2026.api.dto.VeiculoRequest;
import com.curso.estacionamento2026.api.dto.VeiculoResponse;
import com.curso.estacionamento2026.api.mapper.VeiculoMapper;
import com.curso.estacionamento2026.application.VeiculoService;
import com.curso.estacionamento2026.domain.Veiculo;
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
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoService service;
    private final VeiculoMapper mapper;

    public VeiculoController(
            VeiculoService service,
            VeiculoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> cadastrar(
            @Valid @RequestBody VeiculoRequest request) {

        Veiculo veiculo = mapper.toEntity(request);

        Veiculo cadastrado = service.cadastrar(
                veiculo,
                request.tipoVeiculoId()
        );

        URI location = URI.create(
                "/api/veiculos/" + cadastrado.getId()
        );

        return ResponseEntity
                .created(location)
                .body(mapper.toResponse(cadastrado));
    }

    @GetMapping("/{id}")
    public VeiculoResponse buscarPorId(@PathVariable Long id) {
        return mapper.toResponse(service.buscarPorId(id));
    }

    @GetMapping
    public List<VeiculoResponse> listar() {
        return service.listar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}