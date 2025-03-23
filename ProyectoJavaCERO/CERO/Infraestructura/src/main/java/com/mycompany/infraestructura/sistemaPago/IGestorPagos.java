
package com.mycompany.infraestructura.sistemaPago;

import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;

/**
 *
 * @author victoria
 */
public interface IGestorPagos {
    public abstract PagoRealizadoDTO registrarPago(NuevoPagoTarjetaDTO pago);
}
