package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author daniel
 */
public class Contenido {
    
    private Integer id;
    private String nombre;
    private String autor;
    private LocalDateTime fechaHora;
    private byte[] contenido;
    private Clase clase;

    public Contenido() {
    }

    public Contenido(Integer id, String nombre, String autor, LocalDateTime fechaHora, byte[] contenido, Clase clase) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.clase = clase;
    }

    public Contenido(String nombre, String autor, LocalDateTime fechaHora, byte[] contenido, Clase clase) {
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.clase = clase;
    }

    public Contenido(Integer id, String nombre, String autor, LocalDateTime fechaHora, Clase clase) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.clase = clase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        return "Contenido{" + "id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", fechaHora=" + fechaHora + ", clase=" + clase + '}';
    }
    
}
