package com.mycompany.BOs;

import InterfazDAOs.IClasesDAO;
import com.mycompany.InterfazBO.IClasesBO;

/**
 *
 * @author victoria
 */
public class ClasesBO implements IClasesBO{
    private IClasesDAO clasesDAO;

    public ClasesBO(IClasesDAO clasesDAO) {
        this.clasesDAO = clasesDAO;
    }
    
    
}
