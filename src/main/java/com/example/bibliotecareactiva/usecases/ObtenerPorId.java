package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ObtenerPorId {

    Mono<RecursoDTO> get(String id);
}
