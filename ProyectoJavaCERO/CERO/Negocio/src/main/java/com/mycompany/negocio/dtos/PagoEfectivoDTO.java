
package com.mycompany.negocio.dtos;

/**
 * Clase de transporte para un pago con efectivo.
 * @author victoria
 */
public class PagoEfectivoDTO implements MetodoPagoDTO {
    
    private Float cantidadRecibida;
    private Float cambio;

    public PagoEfectivoDTO(Float cantidadRecibida, Float cambio) {
        this.cantidadRecibida = cantidadRecibida;
        this.cambio = cambio;
    }

    public Float getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Float cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Float getCambio() {
        return cambio;
    }

    public void setCambio(Float cambio) {
        this.cambio = cambio;
    }
    
    
}
