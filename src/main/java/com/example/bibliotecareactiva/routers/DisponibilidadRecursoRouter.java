package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.usecases.DisponibilidadRecursoCasoUso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DisponibilidadRecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> disponibilidadRecurso(DisponibilidadRecursoCasoUso disponibilidadRecursoCasoUso){
        return route(GET("/biblioteca/recursoDisponible/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(disponibilidadRecursoCasoUso.apply(request.pathVariable("id")), String.class))
                        .onErrorResume(error -> ServerResponse.badRequest().build()));
    }
}
