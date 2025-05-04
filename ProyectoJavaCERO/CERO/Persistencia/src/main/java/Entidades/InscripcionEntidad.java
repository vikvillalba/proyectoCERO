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
public class InscripcionEntidad {
    private Long id;
    private ClaseEntidad clase;
    private AlumnoEntidad alumno;
    private LocalDate fechaInscripcion;
    private PagoEntidad pago;

    public InscripcionEntidad(Long id, ClaseEntidad clase, AlumnoEntidad alumno, LocalDate fechaInscripcion, PagoEntidad pago) {
        this.id = id;
        this.clase = clase;
        this.alumno = alumno;
        this.fechaInscripcion = fechaInscripcion;
        this.pago = pago;
    }
    
}
