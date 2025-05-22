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
public class EditarClaseDTO extends ClaseAdminDTO {

    private String precio;
    private int cuposDisponibles;
    
    private String fechaInicioStr;
    private String horaInicioStr;
    private String diasStr;
    

    public EditarClaseDTO() {
    }

    public EditarClaseDTO(Integer codigo, String nombreClase, MaestroDTO maestro, String modalidad, AulaClaseDTO aula, List<DayOfWeek> diasClase, LocalTime horaInicio, LocalTime horaFin, LocalDate fechaInicio, LocalDate fechaFin, int capacidadAlumnos, String precio, boolean activa, int cuposDisponibles, String fechaInicioStr, String horaInicioStr,String diasStr) {
        this.codigo = codigo;
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
        this.cuposDisponibles = cuposDisponibles;
        this.fechaInicioStr = fechaInicioStr;
        this.horaInicioStr = horaInicioStr;
        this.diasStr = diasStr;
    }

    public String getDiasStr() {
        return diasStr;
    }

    public void setDiasStr(String diasStr) {
        this.diasStr = diasStr;
    }

    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public String getFechaInicioStr() {
        return fechaInicioStr;
    }

    public void setFechaInicioStr(String fechaInicioStr) {
        this.fechaInicioStr = fechaInicioStr;
    }

    public String getHoraInicioStr() {
        return horaInicioStr;
    }

    public void setHoraInicioStr(String horaInicioStr) {
        this.horaInicioStr = horaInicioStr;
    }
    
}
