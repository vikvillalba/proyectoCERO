
package com.mycompany.negocio.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/**
 * Clase de transporte que representa una clase o taller del colectivo.
 * @author victoria
 */
public class ClaseDTO {
    private int codigo;
    private String nombre;
    private List<DayOfWeek> dias;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String maestro;
    private Float precio;

    public ClaseDTO(String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, String maestro, Float precio) {
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.maestro = maestro;
        this.precio = precio;
    }

    public ClaseDTO(int codigo, String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, String maestro, Float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.maestro = maestro;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DayOfWeek> getDias() {
        return dias;
    }

    public void setDias(List<DayOfWeek> dias) {
        this.dias = dias;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getMaestro() {
        return maestro;
    }

    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    
    
}
