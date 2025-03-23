
package com.mycompany.inscribirclase;

import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.negocio.dtos.InscripcionDTO;
import com.mycompany.negocio.dtos.NuevaInscripcionDTO;
import com.mycompany.negocio.dtos.NuevoPagoDTO;
import com.mycompany.negocio.dtos.PagoDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * Interfaz que abstrae las operaciones para el caso de uso de inscribir alumnos en una clase.
 * @author victoria
 */
public interface IInscribirClase {
    
    /**  valida el efectivo recibido. */
    public abstract boolean validarEfectivoRecibido(BigDecimal costoClase, BigDecimal efectivo);
    
    /**  Calcula el cambio para el pago en efectivo. */
    public abstract BigDecimal calcularCambio(BigDecimal costoClase, BigDecimal cantidadRecibida);
    
    /**  Procesa el pago en efectivo. */
    public abstract PagoDTO realizarPagoEfectivo(NuevoPagoDTO nuevoPago);
       
    /**  Crea conexión al sistema de pagos en infraestructura. */
    public abstract PagoRealizadoDTO confirmarPagoTarjeta(NuevoPagoTarjetaDTO pagoTarjeta);
    
    /** registra el pago con tarjeta. */
    public abstract PagoDTO realizarPagoTarjeta(NuevoPagoDTO nuevoPago);
    
    /** valida que el numero de cuenta sea de 16 caracteres, y que no esté vacío. */
    public abstract boolean validarNumeroCuenta(String numeroCuenta);
    
    /** valida que el propietario no esté vacío. */
    public abstract boolean validarPropietarioTarjeta(String propietario);
    
    /** valida que la tarjeta no esté expirada, y que no esté vacía. */
    public abstract boolean validarFechaExpiracion(LocalDate fecha);
    
    /** valida que el cvv sea de 4 dígitos, y que no esté vacío. */
    public abstract boolean validarCVV(int cvv);
    
    /** Procesa una nueva Inscripción.  */ 
    public abstract InscripcionDTO realizarInscripcion(NuevaInscripcionDTO inscripcion);
}
