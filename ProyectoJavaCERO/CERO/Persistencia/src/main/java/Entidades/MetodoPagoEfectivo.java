package Entidades;

import java.math.BigDecimal;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */
public class MetodoPagoEfectivo extends MetodoPago {
    
    private BigDecimal cantidadRecibida;
    private BigDecimal cambio;
    
    public MetodoPagoEfectivo(ObjectId id,BigDecimal cantidadRecibida, BigDecimal cambio) {
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
