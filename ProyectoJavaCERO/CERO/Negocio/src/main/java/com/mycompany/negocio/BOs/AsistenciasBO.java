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

        Clase clase = clasesDAO.buscarClase(claseDTO.getCodigo());

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

        AsistenciaDTO asistenciaDTO = new AsistenciaDTO(asistenciaRegistrada.getId(), alumnoDTO, claseDTO, nuevaAsistencia.getTipoAsistencia(), asistencia.getFechaHora());
        return asistenciaDTO;

    }

    @Override
    public AsistenciaDTO obtenerAsistenciaAlumnoClase(AlumnoDTO alumnoDTO, ClaseDTO claseDTO) {
        Clase clase = clasesDAO.buscarClase(claseDTO.getCodigo());

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
            AsistenciaDTO asistenciaDTO = new AsistenciaDTO(asistencia.getId(), alumnoDTO, claseDTO, asistencia.getFechaHora());
            return asistenciaDTO;
        }
        return null;
    }

    @Override
    public List<AsistenciaDTO> obtenerAsistenciasClase(ClaseDTO claseDTO, LocalDate diaClase) {
        Clase clase = this.clasesDAO.buscarClase(claseDTO.getCodigo());

        List<Asistencia> asistencias = this.asistenciasDAO.obtenerAsistenciasAlumnos(clase, diaClase);
        if (asistencias == null || asistencias.isEmpty()) {
            return new ArrayList<>();
        }

        List<AsistenciaDTO> asistenciasDTO = new ArrayList<>();
        for (Asistencia asistencia : asistencias) {
            AlumnoDTO alumno = new AlumnoDTO(
                    asistencia.getAlumno().getCodigo(),
                    asistencia.getAlumno().getApellidoPaterno(),
                    asistencia.getAlumno().getApellidoMaterno(),
                    asistencia.getAlumno().getNombre(),
                    asistencia.getAlumno().getTelefono(),
                    asistencia.getAlumno().getFechaNacimiento(),
                    asistencia.getAlumno().getCorreoElectronico()
            );

            TipoAsistenciaDTO tipo = TipoAsistenciaDTO.valueOf(asistencia.getTipoAsistencia().name());
            AsistenciaDTO asistenciaDTO = new AsistenciaDTO(asistencia.getId(), alumno, claseDTO, tipo, asistencia.getFechaHora());
            asistenciasDTO.add(asistenciaDTO);
        }

        return asistenciasDTO;
    }

    @Override
    public AsistenciaDTO justificarFalta(AsistenciaDTO faltaJustificada) {
        Justificante justificante = new Justificante(faltaJustificada.getJustificante().getMotivo(), faltaJustificada.getJustificante().getFechaHora());
        Alumno alumno = this.alumnosDAO.obtenerAlumno(faltaJustificada.getAlumno().getCodigo());

        Clase clase = this.clasesDAO.buscarClase(faltaJustificada.getClase().getCodigo());

        Asistencia asistenciaJustificada = new Asistencia(
                faltaJustificada.getId(),
                TipoAsistencia.JUSTIFICADO,
                faltaJustificada.getFechaHora(),
                alumno,
                clase
        );

        Asistencia justificanteRegistrado = this.asistenciasDAO.justificarFalta(asistenciaJustificada);
        faltaJustificada.setTipoAsistencia(TipoAsistenciaDTO.JUSTIFICADO);
        return faltaJustificada;

    }

    @Override
    public List<AsistenciaDTO> obtenerFaltasJustificadas(AsistenciaDTO asistencia) {
        List<AsistenciaDTO> asistenciasDTO = new ArrayList<>();
        Alumno alumno = this.alumnosDAO.obtenerAlumno(asistencia.getAlumno().getCodigo());
        Clase clase = this.clasesDAO.buscarClase(asistencia.getClase().getCodigo());

        List<Asistencia> faltasJustificadas = this.asistenciasDAO.obtenerFaltasJustificadasAlumnoClase(alumno, clase);

        for (Asistencia falta : faltasJustificadas) {
            TipoAsistenciaDTO tipo = TipoAsistenciaDTO.valueOf(falta.getTipoAsistencia().name());
            AlumnoDTO alumnoDTO = asistencia.getAlumno();
            ClaseDTO claseDTO = asistencia.getClase();
            AsistenciaDTO dto = new AsistenciaDTO(
                    falta.getId(),
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
            Alumno alumno = alumnosDAO.obtenerAlumno(dto.getAlumno().getCodigo());
            Clase clase = clasesDAO.buscarClase(dto.getClase().getCodigo());

            if (alumno == null || clase == null) {
                throw new NegocioException("No se pudo encontrar el alumno o la clase para una de las asistencias.");
            }

            TipoAsistencia tipo = TipoAsistencia.valueOf(dto.getTipoAsistencia().name());

            Asistencia asistencia = new Asistencia(
                    dto.getId(),
                    tipo,
                    dto.getFechaHora(),
                    alumno,
                    clase
            );

            asistenciasEntidad.add(asistencia);
        }

        List<Asistencia> asistenciasActualizadas = asistenciasDAO.actualizarAsistencias(asistenciasEntidad);

        List<AsistenciaDTO> asistenciasActualizadasDTO = new ArrayList<>();
        for (Asistencia asistencia : asistenciasActualizadas) {
            AlumnoDTO alumnoDTO = new AlumnoDTO(
                    asistencia.getAlumno().getCodigo(),
                    asistencia.getAlumno().getApellidoPaterno(),
                    asistencia.getAlumno().getApellidoMaterno(),
                    asistencia.getAlumno().getNombre(),
                    asistencia.getAlumno().getTelefono(),
                    asistencia.getAlumno().getFechaNacimiento(),
                    asistencia.getAlumno().getCorreoElectronico()
            );

            ClaseDTO claseDTO = new ClaseDTO(
                    asistencia.getClase().getCodigo(),
                    asistencia.getClase().getNombre(),
                    asistencia.getClase().getDias(),
                    asistencia.getClase().getHoraInicio(),
                    asistencia.getClase().getHoraFin(),
                    asistencia.getClase().getMaestro().getNombreCompleto(),
                    asistencia.getClase().getPrecio(),
                    asistencia.getClase().getFechaInicio(),
                    asistencia.getClase().getFechaFin()
            );

            TipoAsistenciaDTO tipoDTO = TipoAsistenciaDTO.valueOf(asistencia.getTipoAsistencia().name());

            AsistenciaDTO dtoActualizado = new AsistenciaDTO(
                    asistencia.getId(),
                    alumnoDTO,
                    claseDTO,
                    tipoDTO,
                    asistencia.getFechaHora()
            );

            asistenciasActualizadasDTO.add(dtoActualizado);
        }

        return asistenciasActualizadasDTO;
    }

}
