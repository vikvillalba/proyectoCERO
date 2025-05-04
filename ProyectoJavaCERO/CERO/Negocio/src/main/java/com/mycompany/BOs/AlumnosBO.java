
package com.mycompany.BOs;

import InterfazDAOs.IAlumnosDAO;
import com.mycompany.InterfazBO.IAlumnosBO;

/**
 *
 * @author victoria
 */
public class AlumnosBO implements IAlumnosBO{
    private IAlumnosDAO alumnosDAO;

    public AlumnosBO(IAlumnosDAO alumnosDAO) {
        this.alumnosDAO = alumnosDAO;
    }
    
    
    
}
