package com.mycompany.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Clase de transporte que representa un pago de una inscripción.
 * @author victoria
 */
public class PagoDTO {
    
    private int codigo;
    private BigDecimal total;
    private MetodoPagoDTO metodoPago;
    private LocalDateTime fechaHora;
    private boolean realizado;

    public PagoDTO() {
    }

    public PagoDTO(int codigo, BigDecimal total, MetodoPagoDTO metodoPago, LocalDateTime fechaHora, boolean realizado) {
        this.codigo = codigo;
        this.total = total;
        this.metodoPago = metodoPago;
        this.fechaHora = fechaHora;
        this.realizado = realizado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }


    
    
}
