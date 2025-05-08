package com.mycompany.BOs;

import com.mycompany.InterfazBO.IInscripcionesBO;
import DAOs.IInscripcionesDAO;
import Entidades.Alumno;
import Entidades.Clase;
import Entidades.Inscripcion;
import Entidades.MetodoPago;
import Entidades.MetodoPagoEfectivo;
import Entidades.Pago;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NuevaInscripcionDTO;
import com.mycompany.dtos.PagoDTO;
import com.mycompany.dtos.PagoEfectivoDTO;
import java.time.LocalDateTime;

/**
 *
 * @author victoria
 */
public class InscripcionesBO implements IInscripcionesBO{
    private IInscripcionesDAO inscripcionesDAO;

    public InscripcionesBO(IInscripcionesDAO inscripcionesDAO) {
        this.inscripcionesDAO = inscripcionesDAO;
    }

    // 1 = pago efectivo,  2 = pago tarjeta
    @Override
    public InscripcionDTO registrarInscripcionPagoEfectivo(NuevaInscripcionDTO nuevaInscripcionDTO) {

        ClaseDTO claseDTO =  nuevaInscripcionDTO.getClase();
        Clase clase = new Clase(claseDTO.getCodigo(), claseDTO.getNombre(), claseDTO.getDias(), claseDTO.getHoraInicio(), claseDTO.getHoraFin(), claseDTO.getMaestro(), claseDTO.getPrecio());
        AlumnoDTO alumnoDTO = nuevaInscripcionDTO.getAlumno();
        Alumno alumno = new Alumno(alumnoDTO.getCodigo(), alumnoDTO.getApellidoPaterno(), alumnoDTO.getApellidoMaterno(), alumnoDTO.getNombre(), alumnoDTO.getTelefono(), alumnoDTO.getFechaNacimiento(), alumnoDTO.getCorreoElectronico());
        PagoDTO pagoDTO = nuevaInscripcionDTO.getPago();
        PagoEfectivoDTO metodoPagoDTO = (PagoEfectivoDTO) nuevaInscripcionDTO.getPago().getMetodoPago();
        MetodoPago metodoPago = new MetodoPagoEfectivo(1, metodoPagoDTO.getCantidadRecibida(), metodoPagoDTO.getCambio());
        Pago pago = new Pago(pagoDTO.getCodigo(), pagoDTO.getTotal(), pagoDTO.getFechaHora(), pagoDTO.isRealizado(), metodoPago);
        
        Inscripcion inscripcion = new Inscripcion(0, clase, alumno, LocalDateTime.now(), pago);
        Inscripcion inscripcionRealizada = this.inscripcionesDAO.registrarInscripcion(inscripcion);
        
        InscripcionDTO inscripcionDTO = new InscripcionDTO(inscripcion.getId(), alumnoDTO, claseDTO, inscripcion.getFechaInscripcion(), pagoDTO);
        return inscripcionDTO;
        
    }
    
    
}
