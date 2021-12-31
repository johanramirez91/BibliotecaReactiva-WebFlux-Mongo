package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class RecomendadosPorAreaCasoUso implements RecomendadosPorArea {

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper mapper;

    public RecomendadosPorAreaCasoUso(RepositorioRecurso repositorioRecurso, RecursoMapper mapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get(String area) {
        return repositorioRecurso.findAll()
                .filter(recurso -> recurso.getTipo().equals(area))
                .map(recurso -> mapper.mapDatoToDTO().apply(recurso));
    }
}
