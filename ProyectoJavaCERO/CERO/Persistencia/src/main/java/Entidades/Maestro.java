/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public class Maestro {
    
    private ObjectId id;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String correoElectronico;
    private String contrasena;
    private List<Clase> clasesImpartidad;

    public Maestro(ObjectId id, String apellidoPaterno, String apellidoMaterno, String nombre, String telefono, LocalDate fechaNacimiento, String correoElectronico, String contrasena, List<Clase> clasesImpartidad) {
        this.id = id;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.clasesImpartidad = clasesImpartidad;
    }

    public Maestro() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Clase> getClasesImpartidad() {
        return clasesImpartidad;
    }

    public void setClasesImpartidad(List<Clase> clasesImpartidad) {
        this.clasesImpartidad = clasesImpartidad;
    }
    
    
    
}
