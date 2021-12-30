package com.example.bibliotecareactiva.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Recurso {

    @Id
    private String id;
    private String titulo;
    private String tipo;
    private String area;
    private boolean disponible = false;
    private LocalDate fecha = null;

    public Recurso() {
    }

    public Recurso(String id, String titulo, String tipo, String area, boolean disponible, LocalDate fecha) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.area = area;
        this.disponible = disponible;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
