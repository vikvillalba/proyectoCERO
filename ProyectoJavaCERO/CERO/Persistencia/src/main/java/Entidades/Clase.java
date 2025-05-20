package Entidades;

import implementaciones.ObjectIDMapper;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
/**
 *
 * @author Jack Murrieta
 */
public class Clase {

    private ObjectId id;
    private Integer codigo;
    private String nombre;
    private ObjectId idMaestro;     // Solo el ID del maestro
    private ObjectId idAula;        // Solo el ID del aula
    private String modalidad;
    private List<DayOfWeek> dias;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int capacidadAlumnos;
    private final Integer LIMITE_FALTAS = 3;
    private String nombreMaestro;  // mostrar en reportes
    private String nombreAula;
    private BigDecimal precio;
    private boolean activa;

    public Clase() {
    }

    // Constructor Nueva Clase
    public Clase(Integer codigo, String nombre, ObjectId idMaestro, ObjectId idAula, String modalidad, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, LocalDate fechaInicio, LocalDate fechaFin, int capacidadAlumnos, BigDecimal precio, boolean activa) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idMaestro = idMaestro;
        this.idAula = idAula;
        this.modalidad = modalidad;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.capacidadAlumnos = capacidadAlumnos;
        this.precio = precio;
        this.activa = activa;
    }

    public Clase(int codigo, String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, BigDecimal precio, LocalDate fechaInicio, LocalDate fechaFin, String maestro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
    }

    public Clase(String nombre, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, String maestro, BigDecimal precio, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;

    }

    public Clase(String nombre, String idMaestro, String idAula, String modalidad, List<DayOfWeek> dias, LocalTime horaInicio, LocalTime horaFin, LocalDate fechaInicio, LocalDate fechaFin, int capacidadAlumnos, BigDecimal precio, boolean activa) {
        this.nombre = nombre;
        this.idMaestro = ObjectIDMapper.toObjectId(idMaestro);
        this.idAula = ObjectIDMapper.toObjectId(idAula);
        this.modalidad = modalidad;
        this.dias = dias;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.capacidadAlumnos = capacidadAlumnos;
        this.precio = precio;
        this.activa = activa;
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

    public ObjectId getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(ObjectId idMaestro) {
        this.idMaestro = idMaestro;
    }

    public ObjectId getIdAula() {
        return idAula;
    }

    public void setIdAula(ObjectId idAula) {
        this.idAula = idAula;
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

    @BsonIgnore
    public String obtenerIdString() {
        return ObjectIDMapper.toString(id);
    }

    @BsonIgnore
    public String getIdMaestroString() {
        return ObjectIDMapper.toString(idMaestro);
    }

    @BsonIgnore
    public String getIdAulaString() {
        return ObjectIDMapper.toString(idAula);
    }

    @Override
    public String toString() {
        return "Clase{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", idMaestro=" + idMaestro + ", idAula=" + idAula + ", modalidad=" + modalidad + ", dias=" + dias + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", capacidadAlumnos=" + capacidadAlumnos + ", LIMITE_FALTAS=" + LIMITE_FALTAS + ", nombreMaestro=" + nombreMaestro + ", nombreAula=" + nombreAula + ", precio=" + precio + ", activa=" + activa + '}';
    }

    @BsonIgnore
    public String getNombreMaestro() {
        return nombreMaestro;
    }

    @BsonIgnore
    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    @BsonIgnore
    public String getNombreAula() {
        return nombreAula;
    }

    @BsonIgnore
    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    @BsonIgnore
    public String getIdClaseString() {
        return ObjectIDMapper.toString(idMaestro);
    }

}
