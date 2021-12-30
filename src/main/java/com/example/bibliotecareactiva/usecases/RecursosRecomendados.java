package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface RecursosRecomendados {

    Flux<RecursoDTO> get(String tipo, String area);
}
