package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class DisponibilidadRecursoCasoUso implements Diponibilidad{

    private final RepositorioRecurso repositorioRecurso;

    public DisponibilidadRecursoCasoUso(RepositorioRecurso repositorioRecurso) {
        this.repositorioRecurso = repositorioRecurso;
    }

    @Override
    public Mono<String> apply(String id) {
        if (Objects.isNull(id)){
            return Mono.empty();
        }
        Mono<Recurso> recursoMono = repositorioRecurso.findById(id);
        return recursoMono.map(recurso ->
                recurso.isDisponible() ?
                        "Recurso no disponible, fue prestado el: "
                        + recurso.getFecha()
                        : "Recurso disponible");
    }
}
