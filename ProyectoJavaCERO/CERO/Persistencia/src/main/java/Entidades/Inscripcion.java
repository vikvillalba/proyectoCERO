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
public class Inscripcion {
    private Long id;
    private Clase clase;
    private Alumno alumno;
    private LocalDate fechaInscripcion;
    private Pago pago;

    public Inscripcion(Long id, Clase clase, Alumno alumno, LocalDate fechaInscripcion, Pago pago) {
        this.id = id;
        this.clase = clase;
        this.alumno = alumno;
        this.fechaInscripcion = fechaInscripcion;
        this.pago = pago;
    }
    
}
