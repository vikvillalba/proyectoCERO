package com.mycompany.BOs;

import InterfazDAOs.IPagosTarjetaDAO;
import com.mycompany.InterfazBO.IPagosTarjetaBO;

/**
 *
 * @author victoria
 */
public class PagosTarjetaBO implements IPagosTarjetaBO{
    private IPagosTarjetaDAO pagosTarjetaDAO;

    public PagosTarjetaBO(IPagosTarjetaDAO pagosTarjetaDAO) {
        this.pagosTarjetaDAO = pagosTarjetaDAO;
    }
    
    
}
