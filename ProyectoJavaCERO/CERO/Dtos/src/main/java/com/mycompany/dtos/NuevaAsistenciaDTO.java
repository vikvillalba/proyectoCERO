package com.mycompany.dtos;

/**
 * Clase de transporte que representa una nueva asistencia de un alumno en una clase.
 * @author victoria
 */
public class NuevaAsistenciaDTO {
    private AlumnoDTO alumno;
    private ClaseDTO clase;
    private TipoAsistenciaDTO tipoAsistencia;

    public NuevaAsistenciaDTO(AlumnoDTO alumno, ClaseDTO clase, TipoAsistenciaDTO tipoAsistencia) {
        this.alumno = alumno;
        this.clase = clase;
        this.tipoAsistencia = tipoAsistencia;
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

    
    
}
