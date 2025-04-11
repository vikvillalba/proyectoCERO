package com.mycompany.dtos;

import java.time.LocalDateTime;

/**
 * Clase de transporte para un pago con tarjeta.
 * recibe parámetros de un pago validado por infraestructura. 
 * @author victoria
 */
public class PagoTarjetaDTO implements MetodoPagoDTO{
    
    private String codigoConfirmacion;
    private LocalDateTime fechaHora;

    public PagoTarjetaDTO(String codigoConfirmacion, LocalDateTime fechaHora) {
        this.codigoConfirmacion = codigoConfirmacion;
        this.fechaHora = fechaHora;
    }

    
    public String getCodigoConfirmacion() {
        return codigoConfirmacion;
    }

    public void setCodigoConfirmacion(String codigoConfirmacion) {
        this.codigoConfirmacion = codigoConfirmacion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    
}
