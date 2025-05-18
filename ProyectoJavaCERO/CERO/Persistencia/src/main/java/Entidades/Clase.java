package Entidades;

import implementaciones.ObjectIDMapper;
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
    private Integer codigo;
    private String nombre;
    private Maestro maestro;
    private String modalidad;
    private List<DayOfWeek> dias;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int capacidadAlumnos;
    private AulaClase aula;
    private final Integer LIMITE_FALTAS = 3;
    private String nombreMaestro;  // PROVISIONAL !!!!!!!
    private BigDecimal precio;
    private boolean activa;

    public Clase() {
    }

    //nueva clase
    public Clase(Integer codigo, String nombre, Maestro maestro, String modalidad, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, LocalDate fechaInicio, LocalDate fechaFin, int capacidadAlumnos, AulaClase aula, BigDecimal precio, boolean activa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.maestro = maestro;
        this.modalidad = modalidad;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.capacidadAlumnos = capacidadAlumnos;
        this.aula = aula;
        this.precio = precio;
        this.activa = activa;
    }

    //obtener clase
    public Clase(ObjectId id, Integer codigo, String nombre, Maestro maestro, String modalidad, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, LocalDate fechaInicio, LocalDate fechaFin, int capacidadAlumnos, AulaClase aula, BigDecimal precio, boolean activa) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.maestro = maestro;
        this.modalidad = modalidad;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.capacidadAlumnos = capacidadAlumnos;
        this.aula = aula;
        this.precio = precio;
        this.activa = activa;
    }

    //Clase para CU_Contenidos
    public Clase(String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, String maestro, BigDecimal precio, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombreMaestro = maestro;
        this.precio = precio;
    }

    public Clase(int codigo, String nombre, String maestro, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, BigDecimal precio, LocalDate fechaInicio, LocalDate fechaFin) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombreMaestro = maestro;
        this.precio = precio;
    }

    public Clase(Integer codigo, String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, LocalDate fechaInicio, LocalDate fechaFin, String nombreMaestro, BigDecimal precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombreMaestro = nombreMaestro;
        this.precio = precio;
    }

    //Clase para Inscripcion
    public Clase(int codigo, String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, BigDecimal precio, LocalDate fechaInicio, LocalDate fechaFin, String maestro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombreMaestro = maestro;
        this.precio = precio;
    }

    

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getNombreMaestro() {
        return nombreMaestro;
    }

    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
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
    
    public String obtenerIdString(){
        return ObjectIDMapper.toString(id);
    }

}
