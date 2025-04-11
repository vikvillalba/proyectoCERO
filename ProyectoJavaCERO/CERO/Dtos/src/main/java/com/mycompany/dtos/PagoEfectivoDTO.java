package com.mycompany.dtos;

import java.math.BigDecimal;

/**
 * Clase de transporte para un pago con efectivo.
 * @author victoria
 */
public class PagoEfectivoDTO implements MetodoPagoDTO {
    
    private BigDecimal cantidadRecibida;
    private BigDecimal cambio;

    public PagoEfectivoDTO(BigDecimal cantidadRecibida, BigDecimal cambio) {
        this.cantidadRecibida = cantidadRecibida;
        this.cambio = cambio;
    }

    public BigDecimal getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(BigDecimal cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public BigDecimal getCambio() {
        return cambio;
    }

    public void setCambio(BigDecimal cambio) {
        this.cambio = cambio;
    }


    
    
}
