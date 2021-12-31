package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListarRecursosCasoUso implements Supplier<Flux<RecursoDTO>> {

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper mapper;

    @Autowired
    public ListarRecursosCasoUso(RepositorioRecurso repositorioRecurso, RecursoMapper mapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return repositorioRecurso.findAll().map(mapper.mapDatoToDTO());
    }
}
