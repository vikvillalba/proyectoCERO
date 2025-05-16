package com.mycompany.dtos;

import java.time.LocalDateTime;

/**
 *
 * @author daniel
 */
public class ContenidoBusquedaDTO {
    
    private String nombre;
    private String autor;
    private LocalDateTime fechaHora;
    private ClaseDTO clase;

    public ContenidoBusquedaDTO() {
    }

    public ContenidoBusquedaDTO(String nombre, String autor, LocalDateTime fechaHora, ClaseDTO clase) {
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.clase = clase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public ClaseDTO getClase() {
        return clase;
    }

    public void setClase(ClaseDTO clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "ContenidoBusquedaDTO{" + "nombre=" + nombre + ", autor=" + autor + ", fechaHora=" + fechaHora + ", clase=" + clase + '}';
    }

}
