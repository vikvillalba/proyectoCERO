/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    
    
}
