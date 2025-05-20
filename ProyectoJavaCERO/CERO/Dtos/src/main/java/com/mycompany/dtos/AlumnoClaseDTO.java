package com.mycompany.dtos;

/**
 *
 * @author victoria
 */
public class AlumnoClaseDTO {
    private Integer numeroLista;
    private Integer codigoAlumno;
    private String nombreAlumno;

    public AlumnoClaseDTO(Integer numeroLista, Integer codigoAlumno, String nombreAlumno) {
        this.numeroLista = numeroLista;
        this.codigoAlumno = codigoAlumno;
        this.nombreAlumno = nombreAlumno;
    }

    public Integer getNumeroLista() {
        return numeroLista;
    }

    public void setNumeroLista(Integer numeroLista) {
        this.numeroLista = numeroLista;
    }

    public Integer getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(Integer codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }
    
    
}
