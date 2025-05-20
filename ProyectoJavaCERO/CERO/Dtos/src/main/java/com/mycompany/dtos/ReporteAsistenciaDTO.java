
package com.mycompany.dtos;

import java.time.LocalDate;

/**
 * Clase de transporte que empaqueta los datos para reportes de asistencias.
 * @author victoria
 */
public class ReporteAsistenciaDTO {
    private Integer idAlumno;
    private String nombre;
    private LocalDate fechaClase;
    private TipoAsistenciaDTO tipoAsistencia;
    private String justificante;

    public ReporteAsistenciaDTO() {
    }

    public ReporteAsistenciaDTO(Integer idAlumno, String nombre, LocalDate fechaClase, TipoAsistenciaDTO tipoAsistencia, String justificante) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.fechaClase = fechaClase;
        this.tipoAsistencia = tipoAsistencia;
        this.justificante = justificante;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaClase() {
        return fechaClase;
    }

    public void setFechaClase(LocalDate fechaClase) {
        this.fechaClase = fechaClase;
    }

    public TipoAsistenciaDTO getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(TipoAsistenciaDTO tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public String getJustificante() {
        return justificante;
    }

    public void setJustificante(String justificante) {
        this.justificante = justificante;
    }
    
    
}

