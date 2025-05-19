package com.mycompany.negocio.BOs;

import DAOs.IAlumnosDAO;
import DAOs.IAsistenciasDAO;
import DAOs.IClasesDAO;
import Entidades.Alumno;
import Entidades.Asistencia;
import Entidades.Clase;
import Entidades.Justificante;
import Entidades.TipoAsistencia;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.AsistenciaDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.NuevaAsistenciaDTO;
import com.mycompany.dtos.TipoAsistenciaDTO;
import com.mycompany.negocio.InterfazBO.IAsistenciasBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz IAsistenciasBO
 *
 * @author victoria
 */
public class AsistenciasBO implements IAsistenciasBO {

    private IAsistenciasDAO asistenciasDAO;
    private IClasesDAO clasesDAO;
    private IAlumnosDAO alumnosDAO;

    public AsistenciasBO(IAsistenciasDAO asistenciasDAO, IClasesDAO clasesDAO, IAlumnosDAO alumnosDAO) {
        this.asistenciasDAO = asistenciasDAO;
        this.clasesDAO = clasesDAO;
        this.alumnosDAO = alumnosDAO;
    }

    @Override
    public AsistenciaDTO registrarAsistencia(NuevaAsistenciaDTO nuevaAsistencia) throws NegocioException {
        ClaseDTO claseDTO = nuevaAsistencia.getClase();
        AlumnoDTO alumnoDTO = nuevaAsistencia.getAlumno();

        Clase clase = clasesDAO.buscarClase(claseDTO.getId());

        Alumno alumno = new Alumno(
                alumnoDTO.getCodigo(),
                alumnoDTO.getApellidoPaterno(),
                alumnoDTO.getApellidoMaterno(),
                alumnoDTO.getNombre(),
                alumnoDTO.getTelefono(),
                alumnoDTO.getFechaNacimiento(),
                alumnoDTO.getCorreoElectronico()
        );

        Asistencia asistencia = new Asistencia(Entidades.TipoAsistencia.ASISTENCIA, LocalDateTime.now(), alumno.getIdString(), clase.obtenerIdString());
        Asistencia asistenciaRegistrada = this.asistenciasDAO.registrarAsistencia(asistencia);

        if (asistenciaRegistrada == null) {
            throw new NegocioException("Ocurrió un error al registrar la asistencia del alumno: " + alumno.getNombre() + " " + alumno.getApellidoPaterno() + " en la clase: " + clase.getNombre());
        }

        AsistenciaDTO asistenciaDTO = new AsistenciaDTO(asistenciaRegistrada.getIdString(), alumnoDTO, claseDTO, nuevaAsistencia.getTipoAsistencia(), asistencia.getFechaHora());
        return asistenciaDTO;

    }

    @Override
    public AsistenciaDTO obtenerAsistenciaAlumnoClase(AlumnoDTO alumnoDTO, ClaseDTO claseDTO) {
        Clase clase = clasesDAO.buscarClase(claseDTO.getId());

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
            AsistenciaDTO asistenciaDTO = new AsistenciaDTO(asistencia.getIdString(), alumnoDTO, claseDTO, asistencia.getFechaHora());
            return asistenciaDTO;
        }
        return null;
    }

    @Override
    public List<AsistenciaDTO> obtenerAsistenciasClase(ClaseDTO claseDTO, LocalDate diaClase) {
        Clase clase = this.clasesDAO.buscarClase(claseDTO.getId());

        List<Asistencia> asistencias = this.asistenciasDAO.obtenerAsistenciasAlumnos(clase, diaClase);
        if (asistencias == null || asistencias.isEmpty()) {
            return new ArrayList<>();
        }

        List<AsistenciaDTO> asistenciasDTO = new ArrayList<>();
        for (Asistencia asistencia : asistencias) {
            Alumno alumnoEntidad = alumnosDAO.obtenerAlumno(asistencia.getIdAlumnoString());
            AlumnoDTO alumno = new AlumnoDTO(
                    alumnoEntidad.getApellidoPaterno(),
                    alumnoEntidad.getApellidoMaterno(),
                    alumnoEntidad.getNombre(),
                    alumnoEntidad.getTelefono(),
                    alumnoEntidad.getFechaNacimiento(),
                    alumnoEntidad.getCorreoElectronico()
            );
            alumno.setId(alumnoEntidad.getIdString());

            TipoAsistenciaDTO tipo = TipoAsistenciaDTO.valueOf(asistencia.getTipoAsistencia().name());
            AsistenciaDTO asistenciaDTO = new AsistenciaDTO(asistencia.getIdString(), alumno, claseDTO, tipo, asistencia.getFechaHora());
            asistenciasDTO.add(asistenciaDTO);
        }

        return asistenciasDTO;
    }

    @Override
    public AsistenciaDTO justificarFalta(AsistenciaDTO faltaJustificada) {
        Justificante justificante = new Justificante(faltaJustificada.getJustificante().getMotivo(), faltaJustificada.getJustificante().getFechaHora());
        Alumno alumno = this.alumnosDAO.obtenerAlumno(faltaJustificada.getAlumno().getId());

        Clase clase = this.clasesDAO.buscarClase(faltaJustificada.getClase().getId());

        Asistencia asistenciaJustificada = new Asistencia(
                TipoAsistencia.JUSTIFICADO,
                faltaJustificada.getFechaHora(),
                alumno.getIdString(),
                clase.obtenerIdString()
        );

        Asistencia justificanteRegistrado = this.asistenciasDAO.justificarFalta(asistenciaJustificada);
        faltaJustificada.setTipoAsistencia(TipoAsistenciaDTO.JUSTIFICADO);
        return faltaJustificada;

    }

    @Override
    public List<AsistenciaDTO> obtenerFaltasJustificadas(AsistenciaDTO asistencia) {
        List<AsistenciaDTO> asistenciasDTO = new ArrayList<>();
        Alumno alumno = this.alumnosDAO.obtenerAlumno(asistencia.getAlumno().getId());
        Clase clase = this.clasesDAO.buscarClase(asistencia.getClase().getId());

        List<Asistencia> faltasJustificadas = this.asistenciasDAO.obtenerFaltasJustificadasAlumnoClase(alumno, clase);

        for (Asistencia falta : faltasJustificadas) {
            TipoAsistenciaDTO tipo = TipoAsistenciaDTO.valueOf(falta.getTipoAsistencia().name());
            AlumnoDTO alumnoDTO = asistencia.getAlumno();
            ClaseDTO claseDTO = asistencia.getClase();
            AsistenciaDTO dto = new AsistenciaDTO(
                    falta.getIdString(),
                    alumnoDTO,
                    claseDTO,
                    tipo,
                    falta.getFechaHora()
            );
            asistenciasDTO.add(dto);
        }

        return asistenciasDTO;

    }

    @Override
    public List<AsistenciaDTO> actualizarAsistencias(List<AsistenciaDTO> asistenciasDTO) throws NegocioException {
        List<Asistencia> asistenciasEntidad = new ArrayList<>();

        for (AsistenciaDTO dto : asistenciasDTO) {
            Alumno alumno = alumnosDAO.obtenerAlumno(dto.getAlumno().getId());
            Clase clase = clasesDAO.buscarClase(dto.getClase().getId());

            if (alumno == null || clase == null) {
                throw new NegocioException("No se pudo encontrar el alumno o la clase para una de las asistencias.");
            }

            TipoAsistencia tipo = TipoAsistencia.valueOf(dto.getTipoAsistencia().name());

            Asistencia asistencia = new Asistencia(
                    tipo,
                    dto.getFechaHora(),
                    alumno.getIdString(),
                    clase.obtenerIdString()
            );

            asistenciasEntidad.add(asistencia);
        }

        List<Asistencia> asistenciasActualizadas = asistenciasDAO.actualizarAsistencias(asistenciasEntidad);

        List<AsistenciaDTO> asistenciasActualizadasDTO = new ArrayList<>();
        for (Asistencia asistencia : asistenciasActualizadas) {
            Alumno alumnoEntidad = alumnosDAO.obtenerAlumno(asistencia.getIdAlumnoString());
            AlumnoDTO alumno = new AlumnoDTO(
                    alumnoEntidad.getApellidoPaterno(),
                    alumnoEntidad.getApellidoMaterno(),
                    alumnoEntidad.getNombre(),
                    alumnoEntidad.getTelefono(),
                    alumnoEntidad.getFechaNacimiento(),
                    alumnoEntidad.getCorreoElectronico()
            );
            alumno.setId(alumnoEntidad.getIdString());

            Clase clase = clasesDAO.buscarClase(asistencia.getIdClaseString());
            ClaseDTO claseDTO = new ClaseDTO(
                    clase.getCodigo(),
                    clase.getNombre(),
                    clase.getDias(),
                    clase.getHoraInicio(),
                    clase.getHoraFin(),
                    clase.getNombreMaestro(),
                    clase.getPrecio(),
                    clase.getFechaInicio(),
                    clase.getFechaFin()
            );

            TipoAsistenciaDTO tipoDTO = TipoAsistenciaDTO.valueOf(asistencia.getTipoAsistencia().name());

            AsistenciaDTO dtoActualizado = new AsistenciaDTO(
                    asistencia.getIdString(),
                    alumno,
                    claseDTO,
                    tipoDTO,
                    asistencia.getFechaHora()
            );
            
            

            asistenciasActualizadasDTO.add(dtoActualizado);
        }

        return asistenciasActualizadasDTO;
    }

}
