/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
}
