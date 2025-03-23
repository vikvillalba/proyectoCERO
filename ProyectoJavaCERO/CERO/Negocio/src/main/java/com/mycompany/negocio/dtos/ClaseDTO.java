
package com.mycompany.negocio.dtos;

import java.math.BigDecimal;
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
    private BigDecimal precio;

    public ClaseDTO(int codigo, String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, String maestro, BigDecimal precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.maestro = maestro;
        this.precio = precio;
    }

    public ClaseDTO(String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, String maestro, BigDecimal precio) {
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

    public String getNombre() {
        return nombre;
    }

    public List<DayOfWeek> getDias() {
        return dias;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public String getMaestro() {
        return maestro;
    }

    public BigDecimal getPrecio() {
        return precio;
    }


    
    
}
