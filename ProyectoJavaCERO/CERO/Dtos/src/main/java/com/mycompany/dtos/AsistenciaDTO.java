
package com.mycompany.dtos;

import java.time.LocalDateTime;

/**
 * Clase de transporte que representa una asistencia registrada de un alumno en una clase.
 * @author victoria
 */
public class AsistenciaDTO {
    private String id; 
    private AlumnoDTO alumno;
    private ClaseDTO clase;
    private TipoAsistenciaDTO tipoAsistencia;
    private LocalDateTime fechaHora;
    private JustificanteDTO justificante;

    public AsistenciaDTO(String id, AlumnoDTO alumno, ClaseDTO clase, TipoAsistenciaDTO tipoAsistencia, LocalDateTime fechaHora) {
        this.alumno = alumno;
        this.clase = clase;
        this.tipoAsistencia = tipoAsistencia;
        this.fechaHora = fechaHora;
        this.id = id;
    }

    public AsistenciaDTO(String id, AlumnoDTO alumno, ClaseDTO clase, LocalDateTime fechaHora) {
        this.alumno = alumno;
        this.clase = clase;
        this.fechaHora = fechaHora;
        this.id = id;
    }

    public AsistenciaDTO(AlumnoDTO alumno, ClaseDTO clase, TipoAsistenciaDTO tipoAsistencia, LocalDateTime fechaHora) {
        this.alumno = alumno;
        this.clase = clase;
        this.tipoAsistencia = tipoAsistencia;
        this.fechaHora = fechaHora;

    }
    

    public AlumnoDTO getAlumno() {
        return alumno;
    }

    public ClaseDTO getClase() {
        return clase;
    }

    public TipoAsistenciaDTO getTipoAsistencia() {
        return tipoAsistencia;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public JustificanteDTO getJustificante() {
        return justificante;
    }

    public void setJustificante(JustificanteDTO justificante) {
        this.justificante = justificante;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipoAsistencia(TipoAsistenciaDTO tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }
    
    
}
