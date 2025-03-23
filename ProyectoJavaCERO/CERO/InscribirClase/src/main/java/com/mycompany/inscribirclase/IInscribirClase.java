
package com.mycompany.inscribirclase;

import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.negocio.dtos.NuevoPagoDTO;
import com.mycompany.negocio.dtos.PagoDTO;
/**
 * Interfaz que abstrae las operaciones para el caso de uso de inscribir alumnos en una clase.
 * @author victoria
 */
public interface IInscribirClase {
    
    // valida el efectivo recibido. posible excepcion en un futuro. 
    public abstract boolean validarEfectivoRecibido(Float costoClase, Float efectivo);
    public abstract Float calcularCambio(Float costoClase, Float cantidadRecibida);
    public abstract PagoDTO realizarPagoEfectivo(NuevoPagoDTO nuevoPago);
    // para conectarse al sistema de pagos en infraestructura
    public abstract PagoRealizadoDTO confirmarPagoTarjeta(NuevoPagoTarjetaDTO pagoTarjeta);
    // registra el pago con tarjeta 
    public abstract PagoDTO realizarPagoTarjeta(NuevoPagoDTO nuevoPago);
    
    
}
