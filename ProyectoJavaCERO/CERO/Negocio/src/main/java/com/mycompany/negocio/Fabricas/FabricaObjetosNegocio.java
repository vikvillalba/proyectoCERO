package com.mycompany.negocio.Fabricas;

import implementaciones.AlumnosDAO;
import implementaciones.ClasesDAO;
import implementaciones.InscripcionesDAO;
import implementaciones.PagosDAO;
import DAOs.IAlumnosDAO;
import DAOs.IClasesDAO;
import DAOs.IInscripcionesDAO;
import DAOs.IPagosDAO;
import com.mycompany.BOs.AlumnosBO;
import com.mycompany.BOs.ClasesBO;
import com.mycompany.BOs.InscripcionesBO;
import com.mycompany.BOs.PagosBO;
import com.mycompany.InterfazBO.IAlumnosBO;
import com.mycompany.InterfazBO.IClasesBO;
import com.mycompany.InterfazBO.IInscripcionesBO;
import com.mycompany.InterfazBO.IPagosBO;

/**
 * Fábrica para objetos de negocio.
 *
 * @author victoria
 */
public class FabricaObjetosNegocio {

    public static IAlumnosBO obtenerAlumnosBO() {
        IAlumnosDAO dao = new AlumnosDAO();
        IAlumnosBO bo = new AlumnosBO(dao);
        return bo;
    }

    public static IClasesBO obtenerClasesBO() {
        IClasesDAO dao = new ClasesDAO();
        IClasesBO bo = new ClasesBO(dao);
        return bo;
    }
    
    public static IInscripcionesBO obtenerInscripcionesBO(){
        IInscripcionesDAO dao = new InscripcionesDAO();
        IInscripcionesBO bo = new InscripcionesBO(dao);
        return bo;
    }
    
    public static IPagosBO obtenerPagosBO(){
        IPagosDAO pagosDAO = new PagosDAO();
        IPagosBO bo = new PagosBO(pagosDAO);
        return bo;
    }

}
