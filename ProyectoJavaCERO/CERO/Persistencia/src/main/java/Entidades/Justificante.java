package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author victoria
 */
public class Justificante {
    private String motivo;
    private LocalDateTime fechaHora;

    public Justificante() {
    }
    

    public Justificante(String motivo, LocalDateTime fechaHora) {
        this.motivo = motivo;
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    


    
    

    
}
