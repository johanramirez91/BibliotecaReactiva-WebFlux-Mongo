package com.example.bibliotecareactiva.usecases;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface Diponibilidad {

    Mono<String> apply(String titulo);
}
