package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class MetodoPagoTarjeta extends MetodoPago {
    private Integer id;
    private String codigoCondfirmacion;
    private LocalDateTime fechayHoraPago;

    public MetodoPagoTarjeta(Integer id, String codigoCondfirmacion, LocalDateTime fechayHoraPago) {
        super(id);
        this.id = id;
        this.codigoCondfirmacion = codigoCondfirmacion;
        this.fechayHoraPago = fechayHoraPago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
