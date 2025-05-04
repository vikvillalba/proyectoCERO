package com.mycompany.BOs;

import InterfazDAOs.IPagosEfectivoDAO;
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
