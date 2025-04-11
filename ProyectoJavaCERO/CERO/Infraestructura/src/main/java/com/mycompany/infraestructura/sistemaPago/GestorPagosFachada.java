package com.mycompany.infraestructura.sistemaPago;

import com.mycompany.infraestructura.sistemaPago.implementaciones.GestorPagos;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;

/**
 *
 * @author victoria
 */
public class GestorPagosFachada {
    
    private IGestorPagos gestorPagos;

    public GestorPagosFachada() {
        this.gestorPagos = new GestorPagos();
    }
    
    public PagoRealizadoDTO registrarPago(NuevoPagoTarjetaDTO pago){
       return gestorPagos.registrarPago(pago);
       
    }
    
}
