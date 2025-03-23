
package com.mycompany.negocio.dtos;

import java.math.BigDecimal;

/**
 * Clase de transporte que representa un nuevo pago de una inscripción.
 * @author victoria
 */
public class NuevoPagoDTO {
    
    private BigDecimal total;
    private MetodoPagoDTO metodoPago;

    public NuevoPagoDTO(BigDecimal total, MetodoPagoDTO metodoPago) {
        this.total = total;
        this.metodoPago = metodoPago;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public MetodoPagoDTO getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPagoDTO metodoPago) {
        this.metodoPago = metodoPago;
    }

   
    
    
}
