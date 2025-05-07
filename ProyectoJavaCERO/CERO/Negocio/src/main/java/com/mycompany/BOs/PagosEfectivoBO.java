package com.mycompany.BOs;

import DAOs.IPagosEfectivoDAO;
import com.mycompany.InterfazBO.IPagosEfectivoBO;

/**
 *
 * @author victoria
 */
public class PagosEfectivoBO implements IPagosEfectivoBO{
    private IPagosEfectivoDAO pagosEfectivoDAO;

    public PagosEfectivoBO(IPagosEfectivoDAO pagosEfectivoDAO) {
        this.pagosEfectivoDAO = pagosEfectivoDAO;
    }
    
    
}
