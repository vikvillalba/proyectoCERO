package com.mycompany.dtos;
import java.time.LocalDate;

/**
 * Clase de transporte que representa a un alumno dentro del sistema. 
 * @author victoria
 */
public class AlumnoDTO {
    private String id;
    private Integer codigo;
    private int edad;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String correoElectronico;

    public AlumnoDTO() {
    }

    public AlumnoDTO(Integer codigo, String apellidoPaterno, String apellidoMaterno, String nombre, String telefono, LocalDate fechaNacimiento, String correoElectronico) {
        this.codigo = codigo;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }

    public AlumnoDTO(String apellidoPaterno, String apellidoMaterno, String nombre, String telefono, LocalDate fechaNacimiento, String correoElectronico) {
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }

    //AlumnoDTO de la lista CU_GESTIONAR ALUMNOS
    public AlumnoDTO(String id, Integer codigo,LocalDate fechaNacimiento, int edad, String apellidoPaterno, String apellidoMaterno, String nombre, String telefono, String correoElectronico) {
        this.id = id;
        this.codigo = codigo;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellidoPaterno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
