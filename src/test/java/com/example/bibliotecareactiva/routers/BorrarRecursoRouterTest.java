package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import com.example.bibliotecareactiva.usecases.AgregarRecurso;
import com.example.bibliotecareactiva.usecases.AgregarRecursoCasoUso;
import com.example.bibliotecareactiva.usecases.BorrarRecurso;
import com.example.bibliotecareactiva.usecases.BorrarRecursoCasoUso;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BorrarRecursoRouter.class, BorrarRecursoCasoUso.class, RecursoMapper.class})
class BorrarRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void borrarRecurso(){
        var recurso = new Recurso();
        recurso.setId("111");
        recurso.setTitulo("La odisea");
        recurso.setTipo("Libro");

        when(repositorioRecurso.deleteById(recurso.getId())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/biblioteca/111")
                .exchange()
                .expectStatus().isAccepted()
                .expectBody().isEmpty();
    }
}