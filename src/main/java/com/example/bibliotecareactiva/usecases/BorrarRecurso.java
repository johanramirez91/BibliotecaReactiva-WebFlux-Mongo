package com.example.bibliotecareactiva.usecases;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface BorrarRecurso {

    Mono<Void> get(String id);
}
