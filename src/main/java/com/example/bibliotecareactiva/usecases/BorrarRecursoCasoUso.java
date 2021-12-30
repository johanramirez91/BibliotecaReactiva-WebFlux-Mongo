package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class BorrarRecursoCasoUso implements BorrarRecurso {

    private final RepositorioRecurso repositorioRecurso;

    public BorrarRecursoCasoUso(RepositorioRecurso repositorioRecurso) {
        this.repositorioRecurso = repositorioRecurso;
    }

    @Override
    public Mono<Void> get(String id) {
        if (Objects.isNull(id)){
            return Mono.empty();
        }
        return repositorioRecurso.deleteById(id);
    }
}
