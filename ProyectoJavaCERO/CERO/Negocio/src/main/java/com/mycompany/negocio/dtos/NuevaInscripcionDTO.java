
package com.mycompany.negocio.dtos;

import java.time.LocalDateTime;

/**
 * Clase de transporte que representa una nueva inscripción de un alumno a una clase.
 * @author victoria
 */
public class NuevaInscripcionDTO {
    
    private ClaseDTO clase;
    private AlumnoDTO alumno;
    private LocalDateTime fecha;
    private PagoDTO pago;

    public NuevaInscripcionDTO(ClaseDTO clase, AlumnoDTO alumno, LocalDateTime fecha, PagoDTO pago) {
        this.clase = clase;
        this.alumno = alumno;
        this.fecha = fecha;
        this.pago = pago;
    }

    public ClaseDTO getClase() {
        return clase;
    }

    public void setClase(ClaseDTO clase) {
        this.clase = clase;
    }

    public AlumnoDTO getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoDTO alumno) {
        this.alumno = alumno;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public PagoDTO getPago() {
        return pago;
    }

    public void setPago(PagoDTO pago) {
        this.pago = pago;
    }
    
    
}
