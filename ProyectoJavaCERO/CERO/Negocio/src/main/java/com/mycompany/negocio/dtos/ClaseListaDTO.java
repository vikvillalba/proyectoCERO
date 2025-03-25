/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocio.dtos;

import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public class ClaseListaDTO {
    private List<ClaseListaDTO> clasesExistentes;
    private String id;
    private String nombreClase;
    private String horaInicio;
    private String horaFin;
    private String dias;
    private String maestro;

    public ClaseListaDTO(String id, String nombreClase, String horaInicio, String horaFin, String dias, String maestro) {
        this.id = id;
        this.nombreClase = nombreClase;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dias = dias;
        this.maestro = maestro;
    }

    public ClaseListaDTO() {
        
    }
    

    public List<ClaseListaDTO> getClasesExistentes() {
        return clasesExistentes;
    }

    public void setClasesExistentes(List<ClaseListaDTO> clasesExistentes) {
        this.clasesExistentes = clasesExistentes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getMaestro() {
        return maestro;
    }

    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }
    
    
}
