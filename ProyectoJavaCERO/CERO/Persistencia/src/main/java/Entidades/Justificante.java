package Entidades;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author victoria
 */
public class Justificante {
    private String motivo;
    private Date fechaHora;

    public Justificante() {
    }
    

    public Justificante(String motivo, Date fechaHora) {
        this.motivo = motivo;
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    


    
    

    
}
