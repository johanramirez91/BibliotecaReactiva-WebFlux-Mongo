package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.usecases.RegresarRecursoCasoUso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RegresarRecusoRouter {

    @Bean
    public RouterFunction<ServerResponse> regresarRecurso(RegresarRecursoCasoUso regresarRecursoCasoUso){
        return route(PUT("/biblioteca/regresarRecurso/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(regresarRecursoCasoUso.apply(request.pathVariable("id")), String.class))
                        .onErrorResume(error -> ServerResponse.badRequest().build()));
    }
}
