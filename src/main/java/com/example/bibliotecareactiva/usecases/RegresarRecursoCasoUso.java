package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class RegresarRecursoCasoUso implements Diponibilidad{

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper mapper;
    private final ActualizarRecursoCasoUso actualizarRecursoCasoUso;

    @Autowired
    public RegresarRecursoCasoUso(RepositorioRecurso repositorioRecurso, RecursoMapper mapper, ActualizarRecursoCasoUso actualizarRecursoCasoUso) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapper = mapper;
        this.actualizarRecursoCasoUso = new ActualizarRecursoCasoUso(repositorioRecurso, mapper);
    }

    @Override
    public Mono<String> apply(String id) {
        Mono<Recurso> recursoMono = repositorioRecurso.findById(id);
        return recursoMono.flatMap(recurso -> {
            if (!recurso.isDisponible()){
                recurso.setDisponible(true);
                return repositorioRecurso.save(recurso).thenReturn("Recurso retornado!");
            }
            return Mono.just("No es posible regresar un recurso que no ha sido prestado");
        });
    }
}
