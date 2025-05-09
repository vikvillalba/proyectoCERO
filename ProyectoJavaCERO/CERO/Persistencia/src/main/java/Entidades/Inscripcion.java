
package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class Inscripcion {
    private Integer id;
    private Clase clase;
    private Alumno alumno;
    private LocalDateTime fechaInscripcion;
    private Pago pago;

    public Inscripcion(Integer id, Clase clase, Alumno alumno, LocalDateTime fechaInscripcion, Pago pago) {
        this.id = id;
        this.clase = clase;
        this.alumno = alumno;
        this.fechaInscripcion = fechaInscripcion;
        this.pago = pago;
    }

    public Inscripcion() {
    }

    public Inscripcion(Clase clase, Alumno alumno, LocalDateTime fechaInscripcion, Pago pago) {
        this.clase = clase;
        this.alumno = alumno;
        this.fechaInscripcion = fechaInscripcion;
        this.pago = pago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
    
} 
