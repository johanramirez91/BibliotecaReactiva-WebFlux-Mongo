package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import com.example.bibliotecareactiva.usecases.AgregarRecursoCasoUso;
import org.assertj.core.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AgregarRecursoRouter.class, AgregarRecursoCasoUso.class, RecursoMapper.class})
class AgregarRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void agregarRecurso(){

        var recurso = new Recurso();
        recurso.setId("111");
        recurso.setTitulo("La Odisea");
        recurso.setTipo("Libro");
        recurso.setArea("Novelas");
        recurso.setDisponible(true);
        recurso.setFecha(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(
                recurso.getId(),
                recurso.getTitulo(),
                recurso.getTipo(),
                recurso.getArea(),
                recurso.isDisponible(),
                recurso.getFecha()
        );

        Mono<Recurso> recursoMono = Mono.just(recurso);
        Mockito.when(repositorioRecurso.save(any())).thenReturn(recursoMono);

        webTestClient.post()
                .uri("/biblioteca/agregarRecurso")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Recurso.class)
                .value(response -> {
                    Assertions.assertThat(response.getId()).isEqualTo(recurso.getId());
                });
    }
}