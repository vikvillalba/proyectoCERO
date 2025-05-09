package Entidades;

import java.math.BigDecimal;

/**
 *
 * @author Usuario
 */
public class MetodoPagoEfectivo extends MetodoPago {
    
    private Integer id;
    private BigDecimal cantidadRecibida;
    private BigDecimal cambio;
    
    public MetodoPagoEfectivo(Integer id,BigDecimal cantidadRecibida, BigDecimal cambio) {
        super(id);
        this.cantidadRecibida = cantidadRecibida;
        this.cambio = cambio;
    }

    public MetodoPagoEfectivo() {
    }

    public MetodoPagoEfectivo(BigDecimal cantidadRecibida, BigDecimal cambio) {
        this.cantidadRecibida = cantidadRecibida;
        this.cambio = cambio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
