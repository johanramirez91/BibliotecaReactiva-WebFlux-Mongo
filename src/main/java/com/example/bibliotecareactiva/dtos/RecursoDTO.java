package com.example.bibliotecareactiva.dtos;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Objects;

public class RecursoDTO {

    @Id
    private String id;
    private String titulo;
    private String tipo;
    private String area;
    private boolean disponible = false;
    private LocalDate fecha = null;

    public RecursoDTO() {
    }

    public RecursoDTO(String id, String titulo, String tipo, String area, boolean disponible, LocalDate fecha) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecursoDTO that = (RecursoDTO) o;
        return disponible == that.disponible && Objects.equals(id, that.id) && Objects.equals(titulo, that.titulo) && Objects.equals(tipo, that.tipo) && Objects.equals(area, that.area) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, tipo, area, disponible, fecha);
    }
}
