package com.mycompany.InterfazBO;

import com.mycompany.dtos.NuevoPagoDTO;
import com.mycompany.dtos.PagoDTO;

/**
 *
 * @author victoria
 */
public interface IPagosBO {
    public PagoDTO realizarPagoEfectivo(NuevoPagoDTO nuevoPagoDTO);
    public PagoDTO realizarPagoTarjeta(NuevoPagoDTO nuevoPagoDTO);
    
}
