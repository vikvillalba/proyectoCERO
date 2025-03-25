
package com.mycompany.negocio.dtos;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase de transporte que representa una clase o taller del colectivo.
 * @author victoria
 */
public class ClaseDTO {
    private List<ClaseDTO> clases;
    private int codigo;
    private String nombre;
    private List<DayOfWeek> dias;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String maestro;
    private BigDecimal precio;

    public ClaseDTO() {
        this.clases = new ArrayList<>();
    }

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
    
    public List<ClaseDTO> setClases() {
        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY);

        ClaseDTO clase = new ClaseDTO(
                1, "Contemporanea principiante", dias,
                LocalTime.of(18, 0), LocalTime.of(19, 15),
                "César Díaz", new BigDecimal("500.00")
        );

        ClaseDTO clase1 = new ClaseDTO(
                2, "Ballet Clásico",
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(17, 0), LocalTime.of(19, 0),
                "Maestra Ana Pérez", new BigDecimal("250.00")
        );

        ClaseDTO clase2 = new ClaseDTO(
                3, "Danza Contemporanea",
                Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
                LocalTime.of(18, 0), LocalTime.of(20, 0),
                "Maestro Carlos López", new BigDecimal("300.00")
        );

        ClaseDTO clase3 = new ClaseDTO(
                4, "Folklore Mexicano",
                Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),
                LocalTime.of(16, 0), LocalTime.of(18, 0),
                "Maestra Sofía Ramírez", new BigDecimal("200.00")
        );
        this.clases.add(clase);
        this.clases.add(clase1);
        this.clases.add(clase2);
        this.clases.add(clase3);
        return this.clases;
    }

}
