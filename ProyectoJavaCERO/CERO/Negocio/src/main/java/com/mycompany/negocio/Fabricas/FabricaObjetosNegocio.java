package com.mycompany.negocio.Fabricas;

import implementaciones.AlumnosDAO;
import implementaciones.PagosDAO;
import DAOs.IAlumnosDAO;
import DAOs.IClasesDAO;
import DAOs.IContenidoDAO;
import DAOs.IPagosDAO;
import GestionarClasesPersistencia.AulasClaseDAO;
import GestionarClasesPersistencia.MaestrosDAO;
import implementaciones.ClasesDAO;
import com.mycompany.negocio.BOs.AlumnosBO;
import com.mycompany.negocio.BOs.AsistenciasBO;
import com.mycompany.negocio.BOs.AulaBO;
import com.mycompany.negocio.BOs.ClasesBO;
import com.mycompany.negocio.BOs.ContenidoBO;
import com.mycompany.negocio.BOs.InscripcionesBO;
import com.mycompany.negocio.BOs.MaestroBO;
import com.mycompany.negocio.BOs.PagosBO;
import com.mycompany.negocio.InterfazBO.IAlumnosBO;
import com.mycompany.negocio.InterfazBO.IAsistenciasBO;
import com.mycompany.negocio.InterfazBO.IAulaBO;
import com.mycompany.negocio.InterfazBO.IContenidoBO;
import com.mycompany.negocio.InterfazBO.IInscripcionesBO;
import com.mycompany.negocio.InterfazBO.IMaestroBO;
import com.mycompany.negocio.InterfazBO.IPagosBO;
import implementaciones.AsistenciasDAO;
import implementaciones.ContenidoDAO;
import GestionarClasesPersistencia.IMaestrosDAO;
import GestionarClasesPersistencia.IAulasClaseDAO;
import DAOs.IAsistenciasDAO;
import DAOs.IInscripcionesDAO;
import ObserverInscribirClase.NotificadorInscripcion;
import implementaciones.InscripcionesDAO;
import com.mycompany.negocio.InterfazBO.IClasesBO;


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
        IClasesDAO dao =new ClasesDAO();
        IClasesBO bo = new ClasesBO(dao,obtenerAulaBO(),obtenerMaestroBO());
        return bo;
    }
    
    public static IAulaBO obtenerAulaBO(){
        IAulasClaseDAO dao = new AulasClaseDAO();
        IAulaBO bo = new AulaBO(dao);
        return bo;
    }
    
    public static IMaestroBO obtenerMaestroBO(){
        IMaestrosDAO dao = new MaestrosDAO();
        IMaestroBO bo = new MaestroBO(dao);
        return bo;
    }
    
    public static IInscripcionesBO obtenerInscripcionesBO() {
        IInscripcionesDAO inscripcionesDAO = new InscripcionesDAO();
        IClasesDAO clasesDAO = new ClasesDAO();
        IAlumnosDAO alumnosDAO = new AlumnosDAO();
        IMaestrosDAO maestrosDAO = new MaestrosDAO();

        NotificadorInscripcion notificadorInscripcion = new NotificadorInscripcion();

        // Crear observadores
        IClasesBO clasesBO = obtenerClasesBO();
        IAlumnosBO alumnosBO = obtenerAlumnosBO();


        // Agregar listeners al notificador
        notificadorInscripcion.agregarListener(clasesBO);

        // Crear el BO de inscripciones pasándole el notificador con listeners ya agregados
        IInscripcionesBO bo = new InscripcionesBO(inscripcionesDAO, clasesDAO, alumnosDAO, maestrosDAO, notificadorInscripcion);

        return bo;
    }
    
    public static IPagosBO obtenerPagosBO(){
        IPagosDAO pagosDAO = new PagosDAO();
        IPagosBO bo = new PagosBO(pagosDAO);
        return bo;
    }
    
    public static IAsistenciasBO obtenerAsistenciasBO(){
        IAsistenciasDAO asistenciasDAO = new AsistenciasDAO();
        IClasesDAO clasesDAO = new ClasesDAO();
        IAlumnosDAO alumnosDAO = new AlumnosDAO();
        IAsistenciasBO bo = new AsistenciasBO(asistenciasDAO, clasesDAO, alumnosDAO);
        return bo;
    }
    
    public static IContenidoBO obtenerContenidoBO() {
        IContenidoDAO dao = new ContenidoDAO();
        IContenidoBO bo = new ContenidoBO(dao);
        return bo;
    }
}
