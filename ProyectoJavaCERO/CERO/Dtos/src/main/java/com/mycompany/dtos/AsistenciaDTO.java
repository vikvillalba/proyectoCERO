
package com.mycompany.dtos;

import java.time.LocalDateTime;

/**
 * Clase de transporte que representa una asistencia registrada de un alumno en una clase.
 * @author victoria
 */
public class AsistenciaDTO {
    private AlumnoDTO alumno;
    private ClaseDTO clase;
    private TipoAsistencia tipoAsistencia;
    private LocalDateTime fechaHora;

    public AsistenciaDTO(AlumnoDTO alumno, ClaseDTO clase, TipoAsistencia tipoAsistencia, LocalDateTime fechaHora) {
        this.alumno = alumno;
        this.clase = clase;
        this.tipoAsistencia = tipoAsistencia;
        this.fechaHora = fechaHora;
    }

    public AsistenciaDTO(AlumnoDTO alumno, ClaseDTO clase, LocalDateTime fechaHora) {
        this.alumno = alumno;
        this.clase = clase;
        this.fechaHora = fechaHora;
    }
    

    public AlumnoDTO getAlumno() {
        return alumno;
    }

    public ClaseDTO getClase() {
        return clase;
    }

    public TipoAsistencia getTipoAsistencia() {
        return tipoAsistencia;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    
}
