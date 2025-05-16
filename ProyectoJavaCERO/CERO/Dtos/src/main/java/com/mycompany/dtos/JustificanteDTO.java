package com.mycompany.dtos;

import java.time.LocalDateTime;

/**
 * Clase de transporte que representa una justificación a una falta en una clase.
 * @author victoria
 */
public class JustificanteDTO {
    private String motivo;
    private LocalDateTime fechaHora;

    public JustificanteDTO(String motivo, LocalDateTime fechaHora) {
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
