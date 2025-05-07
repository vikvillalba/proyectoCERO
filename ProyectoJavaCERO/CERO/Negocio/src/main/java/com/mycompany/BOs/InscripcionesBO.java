package com.mycompany.BOs;

import com.mycompany.InterfazBO.IInscripcionesBO;
import DAOs.IInscripcionesDAO;

/**
 *
 * @author victoria
 */
public class InscripcionesBO implements IInscripcionesBO{
    private IInscripcionesDAO inscripcionesDAO;

    public InscripcionesBO(IInscripcionesDAO inscripcionesDAO) {
        this.inscripcionesDAO = inscripcionesDAO;
    }
    
    
}
