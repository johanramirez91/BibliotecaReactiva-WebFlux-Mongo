package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import com.example.bibliotecareactiva.usecases.ListarRecursosCasoUso;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListarRecursosRouter.class, ListarRecursosCasoUso.class, RecursoMapper.class})
class ListarRecursosRouterTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void listaRecursos(){

        var recurso1 = new Recurso();

        recurso1.setId("L123");
        recurso1.setTitulo("Cien años de soledad");
        recurso1.setTipo("Libro");
        recurso1.setArea("Novela");
        recurso1.setDisponible(true);
        recurso1.setFecha(LocalDate.now());

        var recurso2 = new Recurso();

        recurso2.setId("L321");
        recurso2.setTitulo("Hamlet");
        recurso2.setTipo("Libro");
        recurso2.setArea("Drama/Tragedia");
        recurso2.setDisponible(false);
        recurso2.setFecha(LocalDate.now());

        Mockito.when(repositorioRecurso.findAll()).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/biblioteca/listarRecursos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(response -> {
                    Assertions.assertThat(response.get(0).getId()).isEqualTo(recurso1.getId());
                    Assertions.assertThat(response.get(0).getTitulo()).isEqualTo(recurso1.getTitulo());
                    Assertions.assertThat(response.get(0).getTipo()).isEqualTo(recurso1.getTipo());
                    Assertions.assertThat(response.get(0).getArea()).isEqualTo(recurso1.getArea());
                    Assertions.assertThat(response.get(0).isDisponible()).isEqualTo(recurso1.isDisponible());
                    Assertions.assertThat(response.get(0).getFecha()).isEqualTo(recurso1.getFecha());
                    Assertions.assertThat(response.get(1).getId()).isEqualTo(recurso2.getId());
                    Assertions.assertThat(response.get(1).getTipo()).isEqualTo(recurso2.getTipo());
                    Assertions.assertThat(response.get(1).getTitulo()).isEqualTo(recurso2.getTitulo());
                    Assertions.assertThat(response.get(1).getArea()).isEqualTo(recurso2.getArea());
                    Assertions.assertThat(response.get(1).isDisponible()).isEqualTo(recurso2.isDisponible());
                    Assertions.assertThat(response.get(1).getFecha()).isEqualTo(recurso2.getFecha());
                });
    }
}