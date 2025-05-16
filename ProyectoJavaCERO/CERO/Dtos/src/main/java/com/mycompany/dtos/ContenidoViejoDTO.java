package com.mycompany.dtos;

import java.time.LocalDateTime;

/**
 * Representa un contenido obtenido de persistencia.
 * @author daniel
 */
public class ContenidoViejoDTO {
    
    private Object id;
    private String nombre;
    private String autor;
    private LocalDateTime fechaHora;
    private byte[] contenido;
    private ClaseDTO clase;

    public ContenidoViejoDTO() {
    }

    public ContenidoViejoDTO(Object id, String nombre, String autor, LocalDateTime fechaHora, byte[] contenido, ClaseDTO clase) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.clase = clase;
    }

    public ContenidoViejoDTO(Object id, String nombre, String autor, LocalDateTime fechaHora, ClaseDTO clase) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.clase = clase;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
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
        return "ContenidoViejoDTO{" + "id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", fechaHora=" + fechaHora + ", contenido=" + contenido + ", clase=" + clase + '}';
    }
    
}
