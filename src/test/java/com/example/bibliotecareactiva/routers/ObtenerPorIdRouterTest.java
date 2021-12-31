package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import com.example.bibliotecareactiva.usecases.ObtenerPorIdCasoUso;
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
@ContextConfiguration(classes = {ObtenerPorIdRouter.class, ObtenerPorIdCasoUso.class, RecursoMapper.class})
class ObtenerPorIdRouterTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void obtenerPorId(){

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
                .uri("/biblioteca/obtenerPorId/L123")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Recurso.class)
                .value(response -> {
                    Assertions.assertThat(response.getId()).isEqualTo(recurso1.getId());
                    Assertions.assertThat(response.getTitulo()).isEqualTo(recurso1.getTitulo());
                    Assertions.assertThat(response.getTipo()).isEqualTo(recurso1.getTipo());
                    Assertions.assertThat(response.getArea()).isEqualTo(recurso1.getArea());
                    Assertions.assertThat(response.isDisponible()).isEqualTo(recurso1.isDisponible());
                    Assertions.assertThat(response.getFecha()).isEqualTo(recurso1.getFecha());
                });
    }

}