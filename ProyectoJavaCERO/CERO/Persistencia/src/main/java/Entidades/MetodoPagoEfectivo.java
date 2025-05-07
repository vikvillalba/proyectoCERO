package Entidades;

import java.math.BigDecimal;

/**
 *
 * @author Usuario
 */
public class MetodoPagoEfectivo extends MetodoPago {
    
    private Long id;
    private BigDecimal cantidadRecibida;
    private BigDecimal cambio;
    
    public MetodoPagoEfectivo(Long id,BigDecimal cantidadRecibida, BigDecimal cambio) {
        super(id);
        this.cantidadRecibida = cantidadRecibida;
        this.cambio = cambio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
