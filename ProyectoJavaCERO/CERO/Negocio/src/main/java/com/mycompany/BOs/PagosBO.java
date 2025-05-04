package com.mycompany.BOs;

import InterfazDAOs.IPagosDAO;
import com.mycompany.InterfazBO.IPagosBO;

/**
 *
 * @author victoria
 */
public class PagosBO implements IPagosBO{
    
    private IPagosDAO pagosDAO;

    public PagosBO(IPagosDAO pagosDAO) {
        this.pagosDAO = pagosDAO;
    }
    
    
}
