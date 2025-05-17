/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.GestionarClases;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Jack Murrieta
 */
public class EditarClaseDTO {
    private String id;
    private String nombreClase;
    private String nombreMaestro;
    private String modalidad;
    private String aulaNombre;
    private String diasClase;
    private String fechaInicio;
    private String horaInicio;
    private LocalDate fechaFin;
    private LocalTime horaFin;
    private int capacidad;
    private String precio;
    private boolean activa;

    public EditarClaseDTO() {
    }

    public EditarClaseDTO(String id, String nombreClase, String nombreMaestro, String modalidad, String aulaNombre, String diasClase, String fechaInicio, String horaInicio, LocalDate fechaFin, LocalTime horaFin, int capacidad, String precio, boolean activa) {
        this.id = id;
        this.nombreClase = nombreClase;
        this.nombreMaestro = nombreMaestro;
        this.modalidad = modalidad;
        this.aulaNombre = aulaNombre;
        this.diasClase = diasClase;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.capacidad = capacidad;
        this.precio = precio;
        this.activa = activa;
    }

    public EditarClaseDTO(String nombreClase, String nombreMaestro, String modalidad, String aulaNombre, String diasClase, String fechaInicio, String horaInicio, LocalDate fechaFin, LocalTime horaFin, int capacidad, String precio, boolean activa) {
        this.nombreClase = nombreClase;
        this.nombreMaestro = nombreMaestro;
        this.modalidad = modalidad;
        this.aulaNombre = aulaNombre;
        this.diasClase = diasClase;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.capacidad = capacidad;
        this.precio = precio;
        this.activa = activa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreMaestro() {
        return nombreMaestro;
    }

    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getAulaNombre() {
        return aulaNombre;
    }

    public void setAulaNombre(String aulaNombre) {
        this.aulaNombre = aulaNombre;
    }

    public String getDiasClase() {
        return diasClase;
    }

    public void setDiasClase(String diasClase) {
        this.diasClase = diasClase;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }
    
    
}
