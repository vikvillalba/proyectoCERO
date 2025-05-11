package com.mycompany.negocio.BOs;

import DAOs.IInscripcionesDAO;
import Entidades.Alumno;
import Entidades.Clase;
import Entidades.Inscripcion;
import Entidades.MetodoPago;
import Entidades.MetodoPagoEfectivo;
import Entidades.MetodoPagoTarjeta;
import Entidades.Pago;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NuevaInscripcionDTO;
import com.mycompany.dtos.PagoDTO;
import com.mycompany.dtos.PagoEfectivoDTO;
import com.mycompany.dtos.PagoTarjetaDTO;
import com.mycompany.negocio.InterfazBO.IInscripcionesBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victoria
 */
public class InscripcionesBO implements IInscripcionesBO {

    private IInscripcionesDAO inscripcionesDAO;

    public InscripcionesBO(IInscripcionesDAO inscripcionesDAO) {
        this.inscripcionesDAO = inscripcionesDAO;
    }

    @Override
    public InscripcionDTO registrarInscripcionPagoEfectivo(NuevaInscripcionDTO nuevaInscripcionDTO) {

        ClaseDTO claseDTO = nuevaInscripcionDTO.getClase();
        Clase clase = new Clase(
                claseDTO.getCodigo(), 
                claseDTO.getNombre(), 
                claseDTO.getDias(), 
                claseDTO.getHoraInicio(), 
                claseDTO.getHoraFin(), 
                claseDTO.getMaestro(), 
                claseDTO.getPrecio()
        );
        
        AlumnoDTO alumnoDTO = nuevaInscripcionDTO.getAlumno();
        Alumno alumno = new Alumno( 
                alumnoDTO.getCodigo(), 
                alumnoDTO.getApellidoPaterno(),
                alumnoDTO.getApellidoMaterno(), 
                alumnoDTO.getNombre(), 
                alumnoDTO.getTelefono(), 
                alumnoDTO.getFechaNacimiento(),
                alumnoDTO.getCorreoElectronico()
        );
        
        PagoDTO pagoDTO = nuevaInscripcionDTO.getPago();
        PagoEfectivoDTO metodoPagoDTO = (PagoEfectivoDTO) nuevaInscripcionDTO.getPago().getMetodoPago();
        MetodoPago metodoPago = new MetodoPagoEfectivo(metodoPagoDTO.getCantidadRecibida(), metodoPagoDTO.getCambio());
        Pago pago = new Pago(pagoDTO.getCodigo(), pagoDTO.getTotal(), pagoDTO.getFechaHora(), pagoDTO.isRealizado(), metodoPago);

        Inscripcion inscripcion = new Inscripcion(clase, alumno, LocalDateTime.now(), pago);
        Inscripcion inscripcionRealizada = this.inscripcionesDAO.registrarInscripcion(inscripcion);

        InscripcionDTO inscripcionDTO = new InscripcionDTO(inscripcionRealizada.getId(), alumnoDTO, claseDTO, inscripcionRealizada.getFechaInscripcion(), pagoDTO);

        return inscripcionDTO;

    }

    @Override
    public InscripcionDTO registrarInscripcionPagoTarjeta(NuevaInscripcionDTO nuevaInscripcionDTO) {
        ClaseDTO claseDTO = nuevaInscripcionDTO.getClase();
        Clase clase = new Clase(
                claseDTO.getCodigo(), 
                claseDTO.getNombre(), 
                claseDTO.getDias(), 
                claseDTO.getHoraInicio(), 
                claseDTO.getHoraFin(), 
                claseDTO.getMaestro(), 
                claseDTO.getPrecio());
        
        AlumnoDTO alumnoDTO = nuevaInscripcionDTO.getAlumno();
        Alumno alumno = new Alumno(
                alumnoDTO.getCodigo(),
                alumnoDTO.getApellidoPaterno(), 
                alumnoDTO.getApellidoMaterno(), 
                alumnoDTO.getNombre(), 
                alumnoDTO.getTelefono(), 
                alumnoDTO.getFechaNacimiento(), 
                alumnoDTO.getCorreoElectronico()
        );
        
        PagoDTO pagoDTO = nuevaInscripcionDTO.getPago();
        PagoTarjetaDTO metodoPagoDTO = (PagoTarjetaDTO) nuevaInscripcionDTO.getPago().getMetodoPago();
        MetodoPago metodoPago = new MetodoPagoTarjeta(metodoPagoDTO.getCodigoConfirmacion(), metodoPagoDTO.getFechaHora());
        Pago pago = new Pago(pagoDTO.getCodigo(), pagoDTO.getTotal(), pagoDTO.getFechaHora(), pagoDTO.isRealizado(), metodoPago);

        Inscripcion inscripcion = new Inscripcion(clase, alumno, LocalDateTime.now(), pago);
        Inscripcion inscripcionRealizada = this.inscripcionesDAO.registrarInscripcion(inscripcion);

        InscripcionDTO inscripcionDTO = new InscripcionDTO(inscripcionRealizada.getId(), alumnoDTO, claseDTO, inscripcionRealizada.getFechaInscripcion(), pagoDTO);
        return inscripcionDTO;
    }

    @Override
    public List<InscripcionDTO> obtenerInscripcionesAlumno(AlumnoDTO alumnoDTO) throws NegocioException {
        Alumno alumno = new Alumno(
                alumnoDTO.getCodigo(), 
                alumnoDTO.getApellidoPaterno(), 
                alumnoDTO.getApellidoMaterno(),
                alumnoDTO.getNombre(), 
                alumnoDTO.getTelefono(), 
                alumnoDTO.getFechaNacimiento(),
                alumnoDTO.getCorreoElectronico()
        );

        List<Inscripcion> todasLasInscripciones = this.inscripcionesDAO.obtenerInscripcionesAlumno(alumno);
        if (todasLasInscripciones.isEmpty()) {
            throw new NegocioException("El alumno: " + alumnoDTO.getNombre() + " " + alumnoDTO.getApellidoPaterno()
                    + " no está inscrito en ninguna clase.");
        }


        List<Inscripcion> inscripcionesHoy = this.inscripcionesDAO.obtenerInscripcionesAlumnoDiaActual(alumno);
        if (inscripcionesHoy.isEmpty()) {
            throw new NegocioException("El alumno: " + alumnoDTO.getNombre() + " " + alumnoDTO.getApellidoPaterno()
                    + " no tiene clases programadas para el día de hoy.");
        }

        List<InscripcionDTO> inscripcionesDTO = new ArrayList<>();

        for (Inscripcion inscripcion : inscripcionesHoy) {
            Clase clase = inscripcion.getClase();
            ClaseDTO claseDTO = new ClaseDTO(clase.getCodigo(), clase.getNombre(), clase.getDias(), clase.getHoraInicio(), clase.getHoraFin(), clase.getMaestro(), clase.getPrecio()); 
            InscripcionDTO inscripcionDTO = new InscripcionDTO(inscripcion.getId(), alumnoDTO, claseDTO, inscripcion.getFechaInscripcion());
            inscripcionesDTO.add(inscripcionDTO);
        }

        return inscripcionesDTO;
    }

}
