/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class PagoEfectivoEntidad {
    private Long id;
    private BigDecimal total;
    private LocalDate fechaPago;
    private MetodoPago metodoPago;

    public PagoEfectivoEntidad(Long id, BigDecimal total, LocalDate fechaPago, MetodoPago metodoPago) {
        this.id = id;
        this.total = total;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
    }
    
    
}
