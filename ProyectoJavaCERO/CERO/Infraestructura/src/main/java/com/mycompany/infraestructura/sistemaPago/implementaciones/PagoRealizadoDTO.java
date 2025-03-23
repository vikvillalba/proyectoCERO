package com.mycompany.infraestructura.sistemaPago.implementaciones;

import java.time.LocalDateTime;

/**
 * Clase de transporte que representa un pago con tarjeta que ya está autorizado y registrado.
 * @author victoria
 */
public class PagoRealizadoDTO {
    private LocalDateTime fechaHora;
    private String codigoConfirmacion;

    public PagoRealizadoDTO(LocalDateTime fechaHora, String codigoConfirmacion) {
        this.fechaHora = fechaHora;
        this.codigoConfirmacion = codigoConfirmacion;

    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getCodigoConfirmacion() {
        return codigoConfirmacion;
    }


    
}
