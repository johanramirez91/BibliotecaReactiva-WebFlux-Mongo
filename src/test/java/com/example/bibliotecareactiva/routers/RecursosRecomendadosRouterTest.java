package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.collections.Recurso;
import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.mappers.RecursoMapper;
import com.example.bibliotecareactiva.repositories.RepositorioRecurso;
import com.example.bibliotecareactiva.usecases.RecursosRecomendadosCasoUso;
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

import java.time.LocalDate;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RecursosRecomendadosRouter.class, RecursosRecomendadosCasoUso.class, RecursoMapper.class})
class RecursosRecomendadosRouterTest {

    @MockBean
    private RepositorioRecurso repositorioRecurso;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void recursosRecomendados() {

        var recurso1 = new Recurso();
        recurso1.setId("111");
        recurso1.setTitulo("La Odisea");
        recurso1.setTipo("Libro");
        recurso1.setArea("Poémas");
        recurso1.setDisponible(false);
        recurso1.setFecha(LocalDate.now());

        var recurso2 = new Recurso();
        recurso2.setId("123");
        recurso2.setTitulo("Hamlet");
        recurso2.setTipo("Libro");
        recurso2.setArea("Tragedia-Drama");
        recurso2.setDisponible(true);
        recurso2.setFecha(LocalDate.now());

        var recurso3 = new Recurso();
        recurso3.setId("321");
        recurso3.setTitulo("Don Quijote");
        recurso3.setTipo("Libro");
        recurso3.setArea("Novelas");
        recurso3.setDisponible(false);
        recurso3.setFecha(LocalDate.now());

        Mockito.when(repositorioRecurso.findAll()).thenReturn(Flux.just(recurso1, recurso2, recurso3));

        webTestClient.get()
                .uri("/biblioteca/recursosRecomendados/Libro/Poémas")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(response -> {
                    Assertions.assertThat(response.get(0).getTitulo()).isEqualTo(recurso1.getTitulo());
                    Assertions.assertThat(response.get(0).getArea()).isEqualTo(recurso1.getArea());
                    Assertions.assertThat(response.get(0).getTipo()).isEqualTo(recurso1.getTipo());
                });
    }

}