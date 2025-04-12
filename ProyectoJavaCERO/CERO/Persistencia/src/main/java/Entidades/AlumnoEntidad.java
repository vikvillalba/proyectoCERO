/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class AlumnoEntidad {
    private Long codigo;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String correoElectronico;

    public AlumnoEntidad(Long codigo, String apellidoPaterno, String apellidoMaterno, String nombre, String telefono, LocalDate fechaNacimiento, String correoElectronico) {
        this.codigo = codigo;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }


}
