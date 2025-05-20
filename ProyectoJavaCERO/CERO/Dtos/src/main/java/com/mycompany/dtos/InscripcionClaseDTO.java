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
    private String idInscricpcion;
    private AlumnoDTO alumno;
    private ClaseListaDTO claseListaDTO;
    private boolean activa;

    public InscripcionClaseDTO(String idInscricpcion, AlumnoDTO alumno, ClaseListaDTO claseListaDTO, boolean activa) {
        this.idInscricpcion = idInscricpcion;
        this.alumno = alumno;
        this.claseListaDTO = claseListaDTO;
        this.activa = activa;
    }

    
    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    

    public String getIdInscricpcion() {
        return idInscricpcion;
    }

    public void setIdInscricpcion(String idInscricpcion) {
        this.idInscricpcion = idInscricpcion;
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
