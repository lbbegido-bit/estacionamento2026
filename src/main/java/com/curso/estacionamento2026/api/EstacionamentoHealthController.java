package com.curso.estacionamento2026.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstacionamentoHealthController {

    @GetMapping("/api/health")
    public String health() {
        return "OK";
    }
}