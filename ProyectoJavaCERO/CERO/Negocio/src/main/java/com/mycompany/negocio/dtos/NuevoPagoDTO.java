
package com.mycompany.negocio.dtos;

/**
 * Clase de transporte que representa un nuevo pago de una inscripción.
 * @author victoria
 */
public class NuevoPagoDTO {
    
    private Float total;
    private MetodoPagoDTO metodoPago;

    public NuevoPagoDTO(Float total, MetodoPagoDTO metodoPago) {
        this.total = total;
        this.metodoPago = metodoPago;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public MetodoPagoDTO getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPagoDTO metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    
}
