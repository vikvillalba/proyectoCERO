package com.mycompany.dtos;

import java.time.LocalDateTime;

/**
 * Representa un contenido a registrar.
 * @author daniel
 */
public class ContenidoNuevoDTO {
    
    private String nombre;
    private String autor;
    private LocalDateTime fechaHora;
    private byte[] contenido;
    private ClaseDTO clase;

    public ContenidoNuevoDTO() {
    }

    public ContenidoNuevoDTO(String nombre, String autor, LocalDateTime fechaHora, byte[] contenido, ClaseDTO clase) {
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
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

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public ClaseDTO getClase() {
        return clase;
    }

    public void setClase(ClaseDTO clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "ContenidoNuevoDTO{" + "nombre=" + nombre + ", autor=" + autor + ", fechaHora=" + fechaHora + ", contenido=" + contenido + ", clase=" + clase + '}';
    }
    
}
