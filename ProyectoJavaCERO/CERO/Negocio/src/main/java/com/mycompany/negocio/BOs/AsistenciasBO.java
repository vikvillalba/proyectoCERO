package com.mycompany.negocio.BOs;

import DAOs.IAsistenciasDAO;
import Entidades.Alumno;
import Entidades.Asistencia;
import Entidades.Clase;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.AsistenciaDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.NuevaAsistenciaDTO;
import com.mycompany.dtos.TipoAsistencia;
import com.mycompany.negocio.InterfazBO.IAsistenciasBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.LocalDateTime;

/**
 * Implementación de la interfaz IAsistenciasBO
 *
 * @author victoria
 */
public class AsistenciasBO implements IAsistenciasBO {

    private IAsistenciasDAO asistenciasDAO;

    public AsistenciasBO(IAsistenciasDAO asistenciasDAO) {
        this.asistenciasDAO = asistenciasDAO;
    }

    @Override
    public AsistenciaDTO registrarAsistencia(NuevaAsistenciaDTO nuevaAsistencia) throws NegocioException {
        ClaseDTO claseDTO = nuevaAsistencia.getClase();
        AlumnoDTO alumnoDTO = nuevaAsistencia.getAlumno();

        Clase clase = new Clase(
                claseDTO.getCodigo(),
                claseDTO.getNombre(),
                claseDTO.getDias(),
                claseDTO.getHoraInicio(),
                claseDTO.getHoraFin(),
                claseDTO.getMaestro(),
                claseDTO.getPrecio(),
                claseDTO.getFechaInicio(),
                claseDTO.getFechaFin()
        );

        Alumno alumno = new Alumno(
                alumnoDTO.getCodigo(),
                alumnoDTO.getApellidoPaterno(),
                alumnoDTO.getApellidoMaterno(),
                alumnoDTO.getNombre(),
                alumnoDTO.getTelefono(),
                alumnoDTO.getFechaNacimiento(),
                alumnoDTO.getCorreoElectronico()
        );

        Asistencia asistencia = new Asistencia(Entidades.TipoAsistencia.ASISTENCIA, LocalDateTime.now(), alumno, clase);
        Asistencia asistenciaRegistrada = this.asistenciasDAO.registrarAsistencia(asistencia);

        if (asistenciaRegistrada == null) {
            throw new NegocioException("Ocurrió un error al registrar la asistencia del alumno: " + alumno.getNombre() + " " + alumno.getApellidoPaterno() + " en la clase: " + clase.getNombre());
        }

        AsistenciaDTO asistenciaDTO = new AsistenciaDTO(alumnoDTO, claseDTO, nuevaAsistencia.getTipoAsistencia(), asistencia.getFechaHora());
        return asistenciaDTO;

    }

    @Override
    public AsistenciaDTO obtenerAsistenciaAlumnoClase(AlumnoDTO alumnoDTO, ClaseDTO claseDTO) {
        Clase clase = new Clase(
                claseDTO.getCodigo(),
                claseDTO.getNombre(),
                claseDTO.getDias(),
                claseDTO.getHoraInicio(),
                claseDTO.getHoraFin(),
                claseDTO.getMaestro(),
                claseDTO.getPrecio(),
                claseDTO.getFechaInicio(),
                claseDTO.getFechaFin()
        );

        Alumno alumno = new Alumno(
                alumnoDTO.getCodigo(),
                alumnoDTO.getApellidoPaterno(),
                alumnoDTO.getApellidoMaterno(),
                alumnoDTO.getNombre(),
                alumnoDTO.getTelefono(),
                alumnoDTO.getFechaNacimiento(),
                alumnoDTO.getCorreoElectronico()
        );

        Asistencia asistencia = this.asistenciasDAO.obtenerAsistenciaAlumnoClase(alumno, clase);
        if (asistencia != null) {
            AsistenciaDTO asistenciaDTO = new AsistenciaDTO(alumnoDTO, claseDTO, asistencia.getFechaHora());
            return asistenciaDTO;
        }
        return null;
    }

}
