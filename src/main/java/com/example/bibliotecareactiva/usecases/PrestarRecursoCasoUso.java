package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Validated
public class PrestarRecursoCasoUso implements Diponibilidad {

    private final RepositorioRecurso repositorioRecurso;

    public PrestarRecursoCasoUso(RepositorioRecurso repositorioRecurso) {
        this.repositorioRecurso = repositorioRecurso;
    }

    @Override
    public Mono<String> apply(String id) {
        Mono<Recurso> recursoMono = repositorioRecurso.findById(id);
        return recursoMono.flatMap(recurso -> {
            if (recurso.isDisponible()){
                recurso.setFecha(LocalDate.now());
                recurso.setDisponible(false);
                return repositorioRecurso.save(recurso).thenReturn("Recurso prestado exitosamente");
            }
            return Mono.just("El recurso no está disponible");
        });
    }
}
