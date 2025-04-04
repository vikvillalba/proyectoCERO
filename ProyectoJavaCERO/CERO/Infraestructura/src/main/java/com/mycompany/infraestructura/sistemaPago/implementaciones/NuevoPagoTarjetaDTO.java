
package com.mycompany.infraestructura.sistemaPago.implementaciones;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Clase de transporte que representa un nuevo pago con tarjeta previo a guardarse.
 * @author victoria
 */
public class NuevoPagoTarjetaDTO {
    private String numeroCuenta;
    private String propietario;
    private LocalDate fechaExpiracion;
    private int cvv;
    private BigDecimal monto;

    public NuevoPagoTarjetaDTO(String numeroCuenta, String propietario, LocalDate fechaExpiracion, int cvv, BigDecimal monto) {
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.monto = monto;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getPropietario() {
        return propietario;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public int getCvv() {
        return cvv;
    }

    public BigDecimal getMonto() {
        return monto;
    }
    
    
}
