package com.mycompany.negocio.BOs;

import DAOs.IAlumnosDAO;
import DAOs.IClasesDAO;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import DAOs.IInscripcionesDAO;
import DTOs.GestionarClases.ClaseListaDTO;
import Entidades.Maestro;
import Excepciones.PersistenciaException;
import GestionarClasesPersistencia.IMaestrosDAO;
import java.math.BigDecimal;
import java.time.LocalTime;
import DTOs.GestionarClases.AlumnoClaseDTO;
import ObserverInscribirClase.NotificadorInscripcion;
import com.mycompany.dtos.InscripcionClaseDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victoria
 */
public class InscripcionesBO implements IInscripcionesBO {

    private IInscripcionesDAO inscripcionesDAO;
    private IClasesDAO clasesDAO;
    private IAlumnosDAO alumnosDAO;
    private IMaestrosDAO maestrosDAO;
    private NotificadorInscripcion notificadorInscripcion;

    public InscripcionesBO(IInscripcionesDAO inscripcionesDAO, IClasesDAO clasesDAO, IAlumnosDAO alumnosDAO, IMaestrosDAO maestrosDAO, NotificadorInscripcion notificadorInscripcion) {
        this.inscripcionesDAO = inscripcionesDAO;
        this.clasesDAO = clasesDAO;
        this.alumnosDAO = alumnosDAO;
        this.maestrosDAO = maestrosDAO;
        this.notificadorInscripcion = notificadorInscripcion;

    }

    @Override
    public InscripcionDTO registrarInscripcionPagoEfectivo(NuevaInscripcionDTO nuevaInscripcionDTO) {

        ClaseDTO claseDTO = nuevaInscripcionDTO.getClase();
        Clase clase = clasesDAO.buscarClaseCodigoInteger(claseDTO.getCodigo());

        AlumnoDTO alumnoDTO = nuevaInscripcionDTO.getAlumno();
        Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(alumnoDTO.getCodigo());

        PagoDTO pagoDTO = nuevaInscripcionDTO.getPago();
        PagoEfectivoDTO metodoPagoDTO = (PagoEfectivoDTO) nuevaInscripcionDTO.getPago().getMetodoPago();
        MetodoPago metodoPago = new MetodoPagoEfectivo(metodoPagoDTO.getCantidadRecibida(), metodoPagoDTO.getCambio());
        Pago pago = new Pago(pagoDTO.getTotal(), pagoDTO.getFechaHora(), pagoDTO.isRealizado(), metodoPago);

        Inscripcion inscripcion = new Inscripcion(clase.obtenerIdString(), alumno.getIdString(), LocalDateTime.now(), pago);
        Inscripcion inscripcionRealizada = this.inscripcionesDAO.registrarInscripcion(inscripcion);

        InscripcionDTO inscripcionDTO = new InscripcionDTO(alumnoDTO, claseDTO, inscripcionRealizada.getFechaInscripcion(), pagoDTO);
        
        //notificar 
        notificadorInscripcion.notificarRegistroInscripcion(alumno, clase);
        return inscripcionDTO;

    }

    @Override
    public InscripcionDTO registrarInscripcionPagoTarjeta(NuevaInscripcionDTO nuevaInscripcionDTO) {
        ClaseDTO claseDTO = nuevaInscripcionDTO.getClase();
        Clase clase = clasesDAO.buscarClaseCodigoInteger(claseDTO.getCodigo());

        AlumnoDTO alumnoDTO = nuevaInscripcionDTO.getAlumno();
        Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(alumnoDTO.getCodigo());

        PagoDTO pagoDTO = nuevaInscripcionDTO.getPago();
        PagoTarjetaDTO metodoPagoDTO = (PagoTarjetaDTO) nuevaInscripcionDTO.getPago().getMetodoPago();
        MetodoPago metodoPago = new MetodoPagoTarjeta(metodoPagoDTO.getCodigoConfirmacion(), metodoPagoDTO.getFechaHora());
        Pago pago = new Pago(pagoDTO.getTotal(), pagoDTO.getFechaHora(), pagoDTO.isRealizado(), metodoPago);

        Inscripcion inscripcion = new Inscripcion(clase.obtenerIdString(), alumno.getIdString(), LocalDateTime.now(), pago);
        Inscripcion inscripcionRealizada = this.inscripcionesDAO.registrarInscripcion(inscripcion);

        InscripcionDTO inscripcionDTO = new InscripcionDTO(alumnoDTO, claseDTO, inscripcionRealizada.getFechaInscripcion(), pagoDTO);
        
        //notificadr
        notificadorInscripcion.notificarCancelacionInscripcion(alumno, clase);
        return inscripcionDTO;
    }

    @Override
    public List<InscripcionDTO> obtenerInscripcionesAlumno(AlumnoDTO alumnoDTO) throws NegocioException {
        Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(alumnoDTO.getCodigo());

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
            try {
                Clase clase = clasesDAO.buscarClase(inscripcion.getIdClaseString());
                Maestro maestro = maestrosDAO.buscarMaestro(clase.getIdMaestroString());
                ClaseDTO claseDTO = new ClaseDTO(clase.getCodigo(),
                        clase.getNombre(),
                        clase.getDias(),
                        clase.getHoraInicio(),
                        clase.getHoraFin(),
                        maestro.getNombreCompleto(),
                        clase.getPrecio(),
                        clase.getFechaInicio(),
                        clase.getFechaFin());
                InscripcionDTO inscripcionDTO = new InscripcionDTO(alumnoDTO, claseDTO, inscripcion.getFechaInscripcion());
                inscripcionesDTO.add(inscripcionDTO);
            } catch (PersistenciaException ex) {
                throw new NegocioException(ex.getMessage());
            }
        }

        return inscripcionesDTO;
    }

    @Override
    public List<InscripcionDTO> obtenerInscripcionesClase(ClaseDTO claseDTO) throws NegocioException {
        Clase clase = clasesDAO.buscarClaseCodigoInteger(claseDTO.getCodigo());
        List<Inscripcion> inscripciones = this.inscripcionesDAO.obtenerInscripcionesClase(clase.obtenerIdString());
        List<InscripcionDTO> inscripcionesClase = new ArrayList<>();
        if (inscripciones.isEmpty() || inscripciones == null) {
            throw new NegocioException("No se encontraron inscripciones para la clase: " + clase.getNombre());
        }

        for (Inscripcion inscripcion : inscripciones) {
            Alumno alumnoEntidad = this.alumnosDAO.obtenerAlumno(inscripcion.getIdAlumnoString());
            AlumnoDTO alumno = new AlumnoDTO(
                    alumnoEntidad.getCodigo(),
                    alumnoEntidad.getApellidoPaterno(),
                    alumnoEntidad.getApellidoMaterno(),
                    alumnoEntidad.getNombre(),
                    alumnoEntidad.getTelefono(),
                    alumnoEntidad.getFechaNacimiento(),
                    alumnoEntidad.getCorreoElectronico()
            );

            InscripcionDTO inscripcionDTO = new InscripcionDTO(alumno, claseDTO, inscripcion.getFechaInscripcion());
            inscripcionesClase.add(inscripcionDTO);

        }
        return inscripcionesClase;
    }
    
    //METODO CU_GESTIONAR CLASES OBTIENE LAS INSCRIPCIONES 

    @Override
    public List<AlumnoClaseDTO> obtenerAlumnosClase(ClaseDTO claseDTO) {
        Clase clase = clasesDAO.buscarClaseCodigoInteger(claseDTO.getCodigo());
        List<Alumno> alumnos = this.inscripcionesDAO.obtenerAlumnosInscritosClase(clase.obtenerIdString());
        Integer numeroLista = 1;
        List<AlumnoClaseDTO> alumnosClase = new ArrayList<>();
        
        for (Alumno alumno : alumnos) {
            AlumnoClaseDTO alumnoDTO = new AlumnoClaseDTO(numeroLista, alumno.getCodigo(), alumno.getNombreCompleto());
            alumnosClase.add(alumnoDTO);
            numeroLista ++;
        }
        return alumnosClase;
    }
    
    //Metodo dar debaja
    @Override
    public void cancelarInscripcion(InscripcionClaseDTO inscripcion){
        String idInscripcion = inscripcion.getIdInscricpcion();
        Alumno alumno = alumnosDAO.obtenerAlumno(inscripcion.getAlumno().getId());
        Clase clase = clasesDAO.buscarClaseCodigoInteger(inscripcion.getClaseListaDTO().getCodigo());
        inscripcionesDAO.cancelarInscripcion(idInscripcion);
        
        notificadorInscripcion.notificarCancelacionInscripcion(alumno, clase);
    }
}
