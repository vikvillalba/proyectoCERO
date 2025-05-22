/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.GestionarClases;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 * clase de transporte dto padre
 */
public abstract class ClaseAdminDTO {

    protected Integer codigo;
    protected String nombreClase;
    protected MaestroDTO maestro;
    protected String modalidad;
    protected AulaClaseDTO aula;

    protected List<DayOfWeek> diasClase;
    protected LocalTime horaInicio;
    protected LocalTime horaFin;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;

    protected int capacidadAlumnos;
    protected boolean activa;

    // Getters y setters
    public Integer getCodigo() {
        return codigo;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public MaestroDTO getMaestro() {
        return maestro;
    }

    public String getModalidad() {
        return modalidad;
    }

    public AulaClaseDTO getAula() {
        return aula;
    }

    public List<DayOfWeek> getDiasClase() {
        return diasClase;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getCapacidadAlumnos() {
        return capacidadAlumnos;
    }

    public boolean isActiva() {
        return activa;
    }
}
