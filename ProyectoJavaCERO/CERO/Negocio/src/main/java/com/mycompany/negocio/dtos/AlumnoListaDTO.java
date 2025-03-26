package com.mycompany.negocio.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class AlumnoListaDTO {
    
    private List<AlumnoDTO> alumnos = new ArrayList();

    public AlumnoListaDTO() {
    }

    public AlumnoListaDTO(List<AlumnoDTO> alumnos) {
        this.alumnos = alumnos;
    }

    public List<AlumnoDTO> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<AlumnoDTO> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "AlumnoListaDTO{" + "alumnos=" + alumnos + '}';
    }
    
}
