package com.example.bibliotecareactiva.usecases;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Configuration
public class RecursosRecomendadosCasoUso implements RecursosRecomendados{

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper mapper;

    public RecursosRecomendadosCasoUso(RepositorioRecurso repositorioRecurso, RecursoMapper mapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get(String tipo, String area) {
        Flux<RecursoDTO> listaRecursos = repositorioRecurso.findAll().map(mapper.mapDatoToDTO());
        if (isTipoValido(tipo, area)){
            return listaRecursos.filter(recursoDTO -> recursoDTO.getTipo().equalsIgnoreCase(tipo));
        }
        if (isAreaValida(tipo, area)){
            return listaRecursos.filter(recursoDTO -> recursoDTO.getArea().equalsIgnoreCase(area));
        }
        if (isTipoYAreaValida(tipo, area)){
            return listaRecursos
                    .filter(recursoDTO -> recursoDTO.getTipo().equalsIgnoreCase(tipo))
                    .filter(recursoDTO -> recursoDTO.getArea().equalsIgnoreCase(area));
        }
        return listaRecursos;
    }

    private boolean isTipoYAreaValida(String tipo, String area) {
        return !tipo.equalsIgnoreCase("none") && !area.equalsIgnoreCase("none");
    }

    private boolean isAreaValida(String tipo, String area) {
        return tipo.equalsIgnoreCase("none") && !area.equalsIgnoreCase("none");
    }

    private boolean isTipoValido(String tipo, String area) {
        return !tipo.equalsIgnoreCase("none") && area.equalsIgnoreCase("none");
    }
}
