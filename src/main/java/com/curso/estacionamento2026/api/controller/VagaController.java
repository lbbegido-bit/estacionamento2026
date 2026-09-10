package com.curso.estacionamento2026.api.controller;

import com.curso.estacionamento2026.api.dto.VagaRequest;
import com.curso.estacionamento2026.api.dto.VagaResponse;
import com.curso.estacionamento2026.api.mapper.VagaMapper;
import com.curso.estacionamento2026.application.VagaService;
import com.curso.estacionamento2026.domain.Vaga;
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
@RequestMapping("/api/vagas")
public class VagaController {

    private final VagaService service;
    private final VagaMapper mapper;

    public VagaController(
            VagaService service,
            VagaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<VagaResponse> cadastrar(
            @Valid @RequestBody VagaRequest request) {

        Vaga vaga = service.cadastrar(mapper.toEntity(request));

        URI location = URI.create(
                "/api/vagas/" + vaga.getId()
        );

        return ResponseEntity
                .created(location)
                .body(mapper.toResponse(vaga));
    }

    @GetMapping("/{id}")
    public VagaResponse buscarPorId(@PathVariable Long id) {
        return mapper.toResponse(service.buscarPorId(id));
    }

    @GetMapping
    public List<VagaResponse> listar() {
        return service.listar()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}