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
 */
public class NuevaClaseDTO {
    private String nombreClase;
    private MaestroDTO maestro;
    private String modalidad;
    private AulaClaseDTO aula;
    
    private List<DayOfWeek> diasClase;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    private int capacidadAlumnos;
    private double precio;
    private boolean activa;

    public NuevaClaseDTO(String nombreClase, MaestroDTO maestro, String modalidad, AulaClaseDTO aula, List<DayOfWeek> diasClase, LocalTime horaInicio, LocalTime horaFin, LocalDate fechaInicio, LocalDate fechaFin, int capacidadAlumnos, double precio, boolean activa) {
        this.nombreClase = nombreClase;
        this.maestro = maestro;
        this.modalidad = modalidad;
        this.aula = aula;
        this.diasClase = diasClase;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.capacidadAlumnos = capacidadAlumnos;
        this.precio = precio;
        this.activa = activa;
    }

    public NuevaClaseDTO() {
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public MaestroDTO getMaestro() {
        return maestro;
    }

    public void setMaestro(MaestroDTO maestro) {
        this.maestro = maestro;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public AulaClaseDTO getAula() {
        return aula;
    }

    public void setAula(AulaClaseDTO aula) {
        this.aula = aula;
    }

    public List<DayOfWeek> getDiasClase() {
        return diasClase;
    }

    public void setDiasClase(List<DayOfWeek> diasClase) {
        this.diasClase = diasClase;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCapacidadAlumnos() {
        return capacidadAlumnos;
    }

    public void setCapacidadAlumnos(int capacidadAlumnos) {
        this.capacidadAlumnos = capacidadAlumnos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public String toString() {
        return "NuevaClaseDTO{" + "nombreClase=" + nombreClase + ", maestro=" + maestro + ", modalidad=" + modalidad + ", aula=" + aula + ", diasClase=" + diasClase + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", capacidadAlumnos=" + capacidadAlumnos + ", precio=" + precio + ", activa=" + activa + '}';
    }
    
    
    
}
