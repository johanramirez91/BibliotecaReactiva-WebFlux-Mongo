package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.usecases.RecursosRecomendadosCasoUso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecursosRecomendadosRouter {

    @Bean
    public RouterFunction<ServerResponse> recursosRecomendados(RecursosRecomendadosCasoUso recursosRecomendadosCasoUso){
        return route(GET("/biblioteca/recursosRecomendados/{tipo}/{area}")
                .and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(recursosRecomendadosCasoUso
                        .get(request.pathVariable("tipo"), request.pathVariable("area")), RecursoDTO.class)));
    }
}
