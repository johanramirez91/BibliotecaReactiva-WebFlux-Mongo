package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RecomendadosPorArea {
    Flux<RecursoDTO> get(String area);
}
