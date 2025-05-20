/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dtos;

import DTOs.GestionarClases.ClaseListaDTO;

/**
 *
 * @author Jack Murrieta
 */
//DTO para CU_GESTIONAR ALUMNO
public class InscripcionClaseDTO {
    private AlumnoDTO alumno;
    private ClaseListaDTO claseListaDTO;

    public InscripcionClaseDTO(AlumnoDTO alumno, ClaseListaDTO claseListaDTO) {
        this.alumno = alumno;
        this.claseListaDTO = claseListaDTO;
    }

    public AlumnoDTO getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoDTO alumno) {
        this.alumno = alumno;
    }

    public ClaseListaDTO getClaseListaDTO() {
        return claseListaDTO;
    }

    public void setClaseListaDTO(ClaseListaDTO claseListaDTO) {
        this.claseListaDTO = claseListaDTO;
    }
    
    
    
}
