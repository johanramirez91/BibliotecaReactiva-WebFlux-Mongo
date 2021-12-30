package com.example.bibliotecareactiva.repositories;

import com.example.bibliotecareactiva.collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRecurso extends ReactiveMongoRepository<Recurso, String> {
}
