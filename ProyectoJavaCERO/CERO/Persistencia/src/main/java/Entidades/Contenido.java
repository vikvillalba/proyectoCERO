package Entidades;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;

/**
 *
 * @author daniel
 */
public class Contenido {
    
    private ObjectId id;
    private String nombre;
    private String autor;
    private LocalDateTime fechaHora;
    private byte[] contenido;
    private Integer codigoClase;

    public Contenido() {
    }

    public Contenido(ObjectId id, String nombre, String autor, LocalDateTime fechaHora, byte[] contenido, Integer codigoClase) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.codigoClase = codigoClase;
    }

    public Contenido(String nombre, String autor, LocalDateTime fechaHora, byte[] contenido, Integer codigoClase) {
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.codigoClase = codigoClase;
    }

    public Contenido(ObjectId id, String nombre, String autor, LocalDateTime fechaHora, Integer codigoClase) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaHora = fechaHora;
        this.codigoClase = codigoClase;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public Integer getCodigoClase() {
        return codigoClase;
    }

    public void setCodigoClase(Integer codigoClase) {
        this.codigoClase = codigoClase;
    }

    @Override
    public String toString() {
        return "Contenido{" + "id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", fechaHora=" + fechaHora + ", clase=" + codigoClase + '}';
    }
    
}
