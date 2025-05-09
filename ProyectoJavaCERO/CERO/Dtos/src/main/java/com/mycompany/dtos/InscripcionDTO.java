package com.mycompany.dtos;
import java.time.LocalDateTime;

/**
 * Clase de transporte que representa a una inscripción en el sistema.
 * @author victoria
 */
public class InscripcionDTO {
    private int codigo;
    private AlumnoDTO alumno;
    private ClaseDTO clase;
    private LocalDateTime fecha;
    private PagoDTO pago;

    public InscripcionDTO(int codigo, AlumnoDTO alumno, ClaseDTO clase, LocalDateTime fecha, PagoDTO pago) {
        this.codigo = codigo;
        this.alumno = alumno;
        this.clase = clase;
        this.fecha = fecha;
        this.pago = pago;
    }

    public InscripcionDTO(AlumnoDTO alumno, ClaseDTO clase, LocalDateTime fecha, PagoDTO pago) {
        this.alumno = alumno;
        this.clase = clase;
        this.fecha = fecha;
        this.pago = pago;
    }

    public InscripcionDTO(int codigo, AlumnoDTO alumno, ClaseDTO clase, LocalDateTime fecha) {
        this.codigo = codigo;
        this.alumno = alumno;
        this.clase = clase;
        this.fecha = fecha;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public AlumnoDTO getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoDTO alumno) {
        this.alumno = alumno;
    }

    public ClaseDTO getClase() {
        return clase;
    }

    public void setClase(ClaseDTO clase) {
        this.clase = clase;
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
