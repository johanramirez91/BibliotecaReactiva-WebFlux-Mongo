package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class ObtenerPorIdCasoUso implements ObtenerPorId {

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper mapper;

    @Autowired
    public ObtenerPorIdCasoUso(RepositorioRecurso repositorioRecurso, RecursoMapper mapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapper = mapper;
    }

    @Override
    public Mono<RecursoDTO> get(String id) {
        return repositorioRecurso.findById(id)
                .map(mapper.mapDatoToDTO());
    }
}
