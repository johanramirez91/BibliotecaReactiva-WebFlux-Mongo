package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AgregarRecursoCasoUso implements AgregarRecurso{

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper mapper;

    public AgregarRecursoCasoUso(RepositorioRecurso repositorioRecurso, RecursoMapper mapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        return repositorioRecurso
                .save(mapper.mapperToDato().apply(recursoDTO))
                .map(recurso -> mapper.mapDatoToDTO().apply(recurso));
    }
}
