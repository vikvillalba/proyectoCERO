package com.mycompany.BOs;

import DAOs.IPagosDAO;
import Entidades.MetodoPago;
import Entidades.MetodoPagoEfectivo;
import Entidades.MetodoPagoTarjeta;
import Entidades.Pago;
import com.mycompany.InterfazBO.IPagosBO;
import com.mycompany.dtos.NuevoPagoDTO;
import com.mycompany.dtos.PagoDTO;
import com.mycompany.dtos.PagoEfectivoDTO;
import com.mycompany.dtos.PagoTarjetaDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author victoria
 */
public class PagosBO implements IPagosBO{
    
    private IPagosDAO pagosDAO;


    public PagosBO(IPagosDAO pagosDAO) {
        this.pagosDAO = pagosDAO;

    }

    @Override
    public PagoDTO realizarPagoEfectivo(NuevoPagoDTO nuevoPagoDTO) {
        PagoEfectivoDTO metodoPagoDTO = (PagoEfectivoDTO) nuevoPagoDTO.getMetodoPago();
        
       
        BigDecimal total = nuevoPagoDTO.getTotal();
        BigDecimal cambio = metodoPagoDTO.getCambio();
        
        MetodoPago metodoPago = new MetodoPagoEfectivo(1, total,cambio);
        LocalDateTime fecha = LocalDateTime.now();
        Pago pago = new Pago(0, total, fecha, false, metodoPago);
        
        Pago pagoRealizado = this.pagosDAO.registrarPago(pago);
        MetodoPago metodoPagoRealizado = new MetodoPagoEfectivo(pago.getMetodoPago().getId(), pago.getTotal(), cambio);
        
        PagoDTO pagoRealizadoDTO = new PagoDTO(pago.getId(), pago.getTotal(), metodoPagoDTO, pago.getFechaPago(), pago.isRealizado());
        return pagoRealizadoDTO;
    }

    @Override
    public PagoDTO realizarPagoTarjeta(NuevoPagoDTO nuevoPagoDTO) {
        PagoTarjetaDTO metodoPagoDTO = (PagoTarjetaDTO) nuevoPagoDTO.getMetodoPago();
        
       
        BigDecimal total = nuevoPagoDTO.getTotal();
        
        MetodoPago metodoPago = new MetodoPagoTarjeta(2, metodoPagoDTO.getCodigoConfirmacion(), metodoPagoDTO.getFechaHora());
        LocalDateTime fecha = LocalDateTime.now();
        Pago pago = new Pago(0, total, fecha, false, metodoPago);
        
        Pago pagoRealizado = this.pagosDAO.registrarPago(pago);
        MetodoPago metodoPagoRealizado = new MetodoPagoTarjeta(pago.getMetodoPago().getId(), metodoPagoDTO.getCodigoConfirmacion(), fecha);
        
        PagoDTO pagoRealizadoDTO = new PagoDTO(pago.getId(), pago.getTotal(), metodoPagoDTO, pago.getFechaPago(), pago.isRealizado());
        return pagoRealizadoDTO;
    }
    
    
}