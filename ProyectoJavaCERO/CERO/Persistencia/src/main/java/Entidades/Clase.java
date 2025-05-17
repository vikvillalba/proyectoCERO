package Entidades;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public class Clase {

    private ObjectId id;
    private String nombre;
    private Maestro maestro;
    private String modalidad;
    private List<DayOfWeek> dias;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private BigDecimal precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int capacidadAlumnos;
    private AulaClase aula;
    private boolean activa;
    private final Integer LIMITE_FALTAS = 3;
    private String nombreMaestro;  // PROVISIONAL !!!!!!!

    public Clase() {
    }

    public Clase(ObjectId id, String nombre, Maestro maestro, String modalidad, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, BigDecimal precio, LocalDate fechaInicio, LocalDate fechaFin, int capacidadAlumnos, AulaClase aula, boolean activa) {
        this.id = id;
        this.nombre = nombre;
        this.maestro = maestro;
        this.modalidad = modalidad;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.capacidadAlumnos = capacidadAlumnos;
        this.aula = aula;
        this.activa = activa;
    }

    public Clase(String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, String maestro, BigDecimal precio, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Maestro getMaestro() {
        return maestro;
    }

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
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

    public AulaClase getAula() {
        return aula;
    }

    public void setAula(AulaClase aula) {
        this.aula = aula;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Integer getLIMITE_FALTAS() {
        return LIMITE_FALTAS;
    }
}
