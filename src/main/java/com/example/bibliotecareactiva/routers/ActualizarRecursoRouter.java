package com.example.bibliotecareactiva.routers;

import com.example.bibliotecareactiva.dtos.RecursoDTO;
import com.example.bibliotecareactiva.usecases.ActualizarRecursoCasoUso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ActualizarRecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> actualizarRecurso(ActualizarRecursoCasoUso actualizarRecursoCasoUso){
        return route(PUT("/biblioteca/actualizarRecurso")
                .and(accept(MediaType.APPLICATION_JSON)),
                request -> request
                        .bodyToMono(RecursoDTO.class)
                        .flatMap(recursoDTO -> actualizarRecursoCasoUso.apply(recursoDTO)
                                .flatMap(resultado -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(resultado))));
    }
}
