package Entidades;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */
public class MetodoPagoTarjeta extends MetodoPago {
    private String codigoCondfirmacion;
    private LocalDateTime fechayHoraPago;

    public MetodoPagoTarjeta(ObjectId id, String codigoCondfirmacion, LocalDateTime fechayHoraPago) {
        super(id);
        this.codigoCondfirmacion = codigoCondfirmacion;
        this.fechayHoraPago = fechayHoraPago;
    }

    public MetodoPagoTarjeta() {
    }

    public MetodoPagoTarjeta(String codigoCondfirmacion, LocalDateTime fechayHoraPago) {
        this.codigoCondfirmacion = codigoCondfirmacion;
        this.fechayHoraPago = fechayHoraPago;
    }

    public String getCodigoCondfirmacion() {
        return codigoCondfirmacion;
    }

    public void setCodigoCondfirmacion(String codigoCondfirmacion) {
        this.codigoCondfirmacion = codigoCondfirmacion;
    }

    public LocalDateTime getFechayHoraPago() {
        return fechayHoraPago;
    }

    public void setFechayHoraPago(LocalDateTime fechayHoraPago) {
        this.fechayHoraPago = fechayHoraPago;
    }

    
    
}
