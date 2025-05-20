package Entidades;

import java.time.LocalDate;

/**
 *
 * @author victoria
 */
public class ReporteAsistencia {

    private Integer idAlumno;
    private String nombre;
    private LocalDate fechaClase;
    private TipoAsistencia tipoAsistencia;
    private String justificante;

    public ReporteAsistencia(Integer idAlumno, String nombre, LocalDate fechaClase, TipoAsistencia tipoAsistencia, String justificante) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.fechaClase = fechaClase;
        this.tipoAsistencia = tipoAsistencia;
        this.justificante = justificante;
    }

    public ReporteAsistencia() {
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

    public TipoAsistencia getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(TipoAsistencia tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public String getJustificante() {
        return justificante;
    }

    public void setJustificante(String justificante) {
        this.justificante = justificante;
    }
    
    
}
