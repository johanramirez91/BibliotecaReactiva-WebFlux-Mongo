package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import com.example.bibliotecareactiva.usecases.PrestarRecursoCasoUso;
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
@ContextConfiguration(classes = {PrestarRecursoRouter.class, PrestarRecursoCasoUso.class, RecursoMapper.class})
class PrestarRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void prestarRecurso(){

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
        Mockito.when(repositorioRecurso.findById(recurso.getId())).thenReturn(recursoMono);
        Mockito.when(repositorioRecurso.save(any())).thenReturn(recursoMono);

        webTestClient.put()
                .uri("/biblioteca/prestarRecurso/111")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response ->
                        Assertions.assertThat(response).isEqualTo("Recurso prestado exitosamente")
                );
    }

    @Test
    void recursoNoPrestado(){

        var recurso = new Recurso();
        recurso.setId("111");
        recurso.setTitulo("La Odisea");
        recurso.setTipo("Libro");
        recurso.setArea("Novelas");
        recurso.setDisponible(false);
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
        Mockito.when(repositorioRecurso.findById(recurso.getId())).thenReturn(recursoMono);
        Mockito.when(repositorioRecurso.save(any())).thenReturn(recursoMono);

        webTestClient.put()
                .uri("/biblioteca/prestarRecurso/111")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response ->
                        Assertions.assertThat(response).isEqualTo("El recurso no está disponible")
                );
    }

}