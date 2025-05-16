package Entidades;

import java.time.LocalDateTime;

/**
 * Representación de una asistencia en el sistema 
 * @author victoria
 */
public class Asistencia {
    private Integer id;
    private TipoAsistencia tipoAsistencia;
    private LocalDateTime fechaHora;
    private Alumno alumno;
    private Clase clase;
    private Justificante justificante;

    public Asistencia() {
    }

    public Asistencia(Integer id, TipoAsistencia tipoAsistencia, LocalDateTime fechaHora, Alumno alumno, Clase clase) {
        this.id = id;
        this.tipoAsistencia = tipoAsistencia;
        this.fechaHora = fechaHora;
        this.alumno = alumno;
        this.clase = clase;
    }

    public Asistencia(TipoAsistencia tipoAsistencia, LocalDateTime fechaHora, Alumno alumno, Clase clase) {
        this.tipoAsistencia = tipoAsistencia;
        this.fechaHora = fechaHora;
        this.alumno = alumno;
        this.clase = clase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoAsistencia getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(TipoAsistencia tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }
    
    
}
