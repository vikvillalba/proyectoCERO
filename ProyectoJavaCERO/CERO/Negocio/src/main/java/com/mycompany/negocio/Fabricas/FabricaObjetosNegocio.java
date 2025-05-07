package com.mycompany.negocio.Fabricas;

import implementaciones.AlumnosDAO;
import implementaciones.ClasesDAO;
import implementaciones.InscripcionesDAO;
import implementaciones.PagosDAO;
import implementaciones.PagosEfectivoDAO;
import implementaciones.PagosTarjetaDAO;
import DAOs.IAlumnosDAO;
import DAOs.IClasesDAO;
import DAOs.IInscripcionesDAO;
import DAOs.IPagosDAO;
import DAOs.IPagosEfectivoDAO;
import DAOs.IPagosTarjetaDAO;
import com.mycompany.BOs.AlumnosBO;
import com.mycompany.BOs.ClasesBO;
import com.mycompany.BOs.InscripcionesBO;
import com.mycompany.BOs.PagosBO;
import com.mycompany.BOs.PagosEfectivoBO;
import com.mycompany.BOs.PagosTarjetaBO;
import com.mycompany.InterfazBO.IAlumnosBO;
import com.mycompany.InterfazBO.IClasesBO;
import com.mycompany.InterfazBO.IInscripcionesBO;
import com.mycompany.InterfazBO.IPagosBO;
import com.mycompany.InterfazBO.IPagosEfectivoBO;
import com.mycompany.InterfazBO.IPagosTarjetaBO;

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
        IPagosDAO dao = new PagosDAO();
        IPagosBO bo = new PagosBO(dao);
        return bo;
    }
    
    public static IPagosEfectivoBO obtenerPagosEfectivoBO(){
        IPagosEfectivoDAO dao = new PagosEfectivoDAO();
        IPagosEfectivoBO bo = new PagosEfectivoBO(dao);
        return bo;
    }
    
    public static IPagosTarjetaBO obtenerPagosTarjetaBO(){
        IPagosTarjetaDAO dao = new PagosTarjetaDAO();
        IPagosTarjetaBO bo = new PagosTarjetaBO(dao);
        return bo;
    }
}
