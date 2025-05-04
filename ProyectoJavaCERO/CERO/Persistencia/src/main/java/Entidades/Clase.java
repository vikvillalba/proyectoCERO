/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Clase {

    private Long codigo;
    private String nombre;
    private List<DayOfWeek> dias;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String maestro;
    private BigDecimal precio;

    public Clase(Long codigo, String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, String maestro, BigDecimal precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.maestro = maestro;
        this.precio = precio;
    }
    
    

}
