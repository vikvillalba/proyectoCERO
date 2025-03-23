
package com.mycompany.inscribirclase.implementaciones;

import com.mycompany.infraestructura.sistemaPago.IGestorPagos;
import com.mycompany.infraestructura.sistemaPago.implementaciones.GestorPagos;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.inscribirclase.IInscribirClase;
import com.mycompany.negocio.dtos.MetodoPagoDTO;
import com.mycompany.negocio.dtos.NuevoPagoDTO;
import com.mycompany.negocio.dtos.PagoDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author victoria
 */
public class InscribirClase implements IInscribirClase{
    
    private List<PagoDTO> pagos = new ArrayList<>();

    @Override
    public Float calcularCambio(Float costoClase, Float cantidadRecibida) {
        return cantidadRecibida - costoClase;
    }

    @Override
    public PagoDTO realizarPagoEfectivo(NuevoPagoDTO nuevoPago) {
        Random random = new Random();
        
        Float total = nuevoPago.getTotal();
        MetodoPagoDTO metodoPago = nuevoPago.getMetodoPago();
        int codigo = random.nextInt(1000) + 1;
        LocalDateTime fecha = LocalDateTime.now();
        PagoDTO pago =  new PagoDTO(codigo, total, metodoPago, fecha, true);
        
        pagos.add(pago);
        return pago;
        
    }


    @Override
    public boolean validarEfectivoRecibido(Float costoClase, Float efectivo) {
        return efectivo >= costoClase;
    }

    @Override
    public PagoRealizadoDTO confirmarPagoTarjeta(NuevoPagoTarjetaDTO pagoTarjeta) {
        IGestorPagos gestorPagosExterno = new GestorPagos();
        return gestorPagosExterno.registrarPago(pagoTarjeta);
    }

    @Override
    public PagoDTO realizarPagoTarjeta(NuevoPagoDTO nuevoPago) {
        Random random = new Random();
        
        Float total = nuevoPago.getTotal();
        MetodoPagoDTO metodoPago = nuevoPago.getMetodoPago();
        int codigo = random.nextInt(1000) + 1;
        LocalDateTime fecha = LocalDateTime.now();
        PagoDTO pago =  new PagoDTO(codigo, total, metodoPago, fecha, true);
        
        pagos.add(pago);
        return pago;
    }


}
