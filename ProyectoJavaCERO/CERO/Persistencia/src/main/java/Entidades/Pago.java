package Entidades;

import implementaciones.ObjectIDMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */
public class Pago {
    private ObjectId id;
    private BigDecimal total;
    private LocalDateTime fechaPago;
    private boolean realizado;
    private MetodoPago metodoPago;

    public Pago() {
    }

    public Pago(BigDecimal total, LocalDateTime fechaPago, boolean realizado, MetodoPago metodoPago) {
        this.total = total;
        this.fechaPago = fechaPago;
        this.realizado = realizado;
        this.metodoPago = metodoPago;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    
    public String getIdString(){
        return ObjectIDMapper.toString(id);
    }
}
