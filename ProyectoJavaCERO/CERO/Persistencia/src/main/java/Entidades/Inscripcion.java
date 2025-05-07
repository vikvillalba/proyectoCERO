
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
    
} 
