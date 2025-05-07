package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class MetodoPagoTarjeta extends MetodoPago {
    private Long id;
    private String codigoCondfirmacion;
    private LocalDateTime fechayHoraPago;

    public MetodoPagoTarjeta(Long id, String codigoCondfirmacion, LocalDateTime fechayHoraPago) {
        super(id);
        this.id = id;
        this.codigoCondfirmacion = codigoCondfirmacion;
        this.fechayHoraPago = fechayHoraPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
