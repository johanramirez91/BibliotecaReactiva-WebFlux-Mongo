package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import com.example.bibliotecareactiva.usecases.DisponibilidadRecursoCasoUso;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DisponibilidadRecursoRouter.class, DisponibilidadRecursoCasoUso.class, RecursoMapper.class})
class DisponibilidadRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void disponibilidadRecurso(){

        var recurso1 = new Recurso();

        recurso1.setId("L123");
        recurso1.setTitulo("Cien años de soledad");
        recurso1.setTipo("Libro");
        recurso1.setArea("Novela");
        recurso1.setDisponible(true);
        recurso1.setFecha(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(
                recurso1.getId(),
                recurso1.getTitulo(),
                recurso1.getTipo(),
                recurso1.getArea(),
                recurso1.isDisponible(),
                recurso1.getFecha()
        );
        Mono<Recurso> recursoMono = Mono.just(recurso1);
        Mockito.when(repositorioRecurso.findById(recurso1.getId())).thenReturn(recursoMono);

        webTestClient.get()
                .uri("/biblioteca/recursoDisponible/L123")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response ->
                    Assertions.assertThat(response).isEqualTo("Recurso disponible")
                );
    }

    @Test
    void recursoNoDisponible(){

        var recurso1 = new Recurso();

        recurso1.setId("L321");
        recurso1.setTitulo("Hamlet");
        recurso1.setTipo("Libro");
        recurso1.setArea("Drama/Tragedia");
        recurso1.setDisponible(false);
        recurso1.setFecha(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(
                recurso1.getId(),
                recurso1.getTitulo(),
                recurso1.getTipo(),
                recurso1.getArea(),
                recurso1.isDisponible(),
                recurso1.getFecha()
        );
        Mono<Recurso> recursoMono = Mono.just(recurso1);
        Mockito.when(repositorioRecurso.findById(recurso1.getId())).thenReturn(recursoMono);

        webTestClient.get()
                .uri("/biblioteca/recursoDisponible/L321")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(response ->
                        Assertions.assertThat(response).isEqualTo("Recurso no disponible, fue prestado el: " + recurso1.getFecha())
                );
    }

}