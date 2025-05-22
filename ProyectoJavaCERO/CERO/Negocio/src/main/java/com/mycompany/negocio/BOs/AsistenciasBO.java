package com.mycompany.negocio.BOs;

import DAOs.IAlumnosDAO;
import DAOs.IAsistenciasDAO;
import DAOs.IClasesDAO;
import DTOs.GestionarClases.ClaseListaDTO;
import Entidades.Alumno;
import Entidades.Asistencia;
import Entidades.Clase;
import Entidades.Justificante;
import Entidades.ReporteAsistencia;
import Entidades.TipoAsistencia;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.AsistenciaDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.NuevaAsistenciaDTO;
import com.mycompany.dtos.ReporteAsistenciaDTO;
import com.mycompany.dtos.TipoAsistenciaDTO;
import com.mycompany.negocio.InterfazBO.IAsistenciasBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

        Clase clase = clasesDAO.buscarClaseCodigoInteger(claseDTO.getCodigo());

        Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(alumnoDTO.getCodigo());
        Asistencia asistencia = new Asistencia(Entidades.TipoAsistencia.ASISTENCIA.toString(), LocalDateTime.now(), alumno.getIdString(), clase.obtenerIdString());
        Asistencia asistenciaRegistrada = this.asistenciasDAO.registrarAsistencia(asistencia);

        if (asistenciaRegistrada == null) {
            throw new NegocioException("Ocurrió un error al registrar la asistencia del alumno: " + alumno.getNombre() + " " + alumno.getApellidoPaterno() + " en la clase: " + clase.getNombre());
        }

        AsistenciaDTO asistenciaDTO = new AsistenciaDTO(asistenciaRegistrada.getIdString(), alumnoDTO, claseDTO, nuevaAsistencia.getTipoAsistencia(), asistencia.getFechaHora());
        return asistenciaDTO;

    }

    @Override
    public AsistenciaDTO obtenerAsistenciaAlumnoClase(AlumnoDTO alumnoDTO, ClaseDTO claseDTO) {
        Clase clase = clasesDAO.buscarClaseCodigoInteger(claseDTO.getCodigo());

        Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(alumnoDTO.getCodigo());

        Asistencia asistencia = this.asistenciasDAO.obtenerAsistenciaAlumnoClase(alumno, clase);
        if (asistencia != null) {
            AsistenciaDTO asistenciaDTO = new AsistenciaDTO(asistencia.getIdString(), alumnoDTO, claseDTO, asistencia.getFechaHora());
            return asistenciaDTO;
        }
        return null;
    }

    @Override
    public List<AsistenciaDTO> obtenerAsistenciasClase(ClaseDTO claseDTO, LocalDate diaClase) {
        Clase clase = this.clasesDAO.buscarClaseCodigoInteger(claseDTO.getCodigo());

        List<Asistencia> asistencias = this.asistenciasDAO.obtenerAsistenciasAlumnos(clase, diaClase);
        if (asistencias == null || asistencias.isEmpty()) {
            return new ArrayList<>();
        }

        List<AsistenciaDTO> asistenciasDTO = new ArrayList<>();
        for (Asistencia asistencia : asistencias) {
            Alumno alumnoEntidad = alumnosDAO.obtenerAlumno(asistencia.getIdAlumnoString());
            AlumnoDTO alumno = new AlumnoDTO(
                    alumnoEntidad.getCodigo(),
                    alumnoEntidad.getApellidoPaterno(),
                    alumnoEntidad.getApellidoMaterno(),
                    alumnoEntidad.getNombre(),
                    alumnoEntidad.getTelefono(),
                    alumnoEntidad.getFechaNacimiento(),
                    alumnoEntidad.getCorreoElectronico()
            );
            alumno.setId(alumnoEntidad.getIdString());

            TipoAsistenciaDTO tipo = TipoAsistenciaDTO.valueOf(asistencia.getTipoAsistencia());
            AsistenciaDTO asistenciaDTO = new AsistenciaDTO(asistencia.getIdString(), alumno, claseDTO, tipo, asistencia.getFechaHora());
            asistenciasDTO.add(asistenciaDTO);
        }

        return asistenciasDTO;
    }

    @Override
    public AsistenciaDTO justificarFalta(AsistenciaDTO faltaJustificada) {
        // De LocalDateTime a Date
        Date fecha = Date.from(faltaJustificada.getJustificante().getFechaHora().atZone(ZoneId.systemDefault()).toInstant());

        Justificante justificante = new Justificante(faltaJustificada.getJustificante().getMotivo(), fecha);
        Alumno alumno = this.alumnosDAO.obtenerAlumno(faltaJustificada.getAlumno().getId());

        Clase clase = this.clasesDAO.buscarClaseCodigoInteger(faltaJustificada.getClase().getCodigo());

        Asistencia asistenciaJustificada = new Asistencia(
                faltaJustificada.getId(),
                TipoAsistencia.JUSTIFICADO.toString(),
                faltaJustificada.getFechaHora(),
                alumno.getIdString(),
                clase.obtenerIdString()
        );

        asistenciaJustificada.setJustificante(justificante);
        Asistencia justificanteRegistrado = this.asistenciasDAO.justificarFalta(asistenciaJustificada);
        faltaJustificada.setTipoAsistencia(TipoAsistenciaDTO.JUSTIFICADO);
        return faltaJustificada;

    }

    @Override
    public List<AsistenciaDTO> obtenerFaltasJustificadas(AsistenciaDTO asistencia) {
        List<AsistenciaDTO> asistenciasDTO = new ArrayList<>();
        Alumno alumno = this.alumnosDAO.obtenerAlumno(asistencia.getAlumno().getId());
        Clase clase = this.clasesDAO.buscarClaseCodigoInteger(asistencia.getClase().getCodigo());

        List<Asistencia> faltasJustificadas = this.asistenciasDAO.obtenerFaltasJustificadasAlumnoClase(alumno, clase);

        for (Asistencia falta : faltasJustificadas) {
            TipoAsistenciaDTO tipo = TipoAsistenciaDTO.valueOf(falta.getTipoAsistencia());
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
    public List<AsistenciaDTO> actualizarAsistencias(List<AsistenciaDTO> asistenciasDTO, ClaseDTO claseDTO) throws NegocioException {

        List<Asistencia> asistenciasEntidad = new ArrayList<>();
        Clase clase = clasesDAO.buscarClaseCodigoInteger(claseDTO.getCodigo());
        String idClase = clase.obtenerIdString();

        for (AsistenciaDTO dto : asistenciasDTO) {
            Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(dto.getAlumno().getCodigo());

            if (alumno == null || clase == null) {
                throw new NegocioException("No se pudo encontrar el alumno o la clase para una de las asistencias.");
            }

            TipoAsistencia tipo = TipoAsistencia.valueOf(dto.getTipoAsistencia().name());

            Asistencia asistencia = new Asistencia(
                    null,
                    tipo.toString(),
                    dto.getFechaHora(),
                    alumno.getIdString(),
                    clase.obtenerIdString()
            );

            if (dto.getId() != null && !dto.getId().isBlank() && dto.getId().length() == 24) {
                asistencia.setIdString(dto.getId());
            } else {
                asistencia.setIdString(null);
            }

            asistenciasEntidad.add(asistencia);

        }

        List<Asistencia> asistenciasActualizadas = asistenciasDAO.actualizarAsistencias(asistenciasEntidad, idClase);

        List<AsistenciaDTO> asistenciasActualizadasDTO = new ArrayList<>();
        for (Asistencia asistencia : asistenciasActualizadas) {
            Alumno alumnoEntidad = alumnosDAO.obtenerAlumno(asistencia.getIdAlumnoString());
            AlumnoDTO alumno = new AlumnoDTO(
                    alumnoEntidad.getCodigo(),
                    alumnoEntidad.getApellidoPaterno(),
                    alumnoEntidad.getApellidoMaterno(),
                    alumnoEntidad.getNombre(),
                    alumnoEntidad.getTelefono(),
                    alumnoEntidad.getFechaNacimiento(),
                    alumnoEntidad.getCorreoElectronico()
            );
            alumno.setId(alumnoEntidad.getIdString());
            TipoAsistenciaDTO tipoDTO = TipoAsistenciaDTO.valueOf(asistencia.getTipoAsistencia());

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

    @Override
    public List<ReporteAsistenciaDTO> obtenerReporteAsistencias(Integer codigoClase, Integer codigoAlumno, LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {
        Clase clase = clasesDAO.buscarClaseCodigoInteger(codigoClase);
        if (clase == null) {
            throw new NegocioException("No existe la clase con código: " + codigoClase);
        }

        List<ReporteAsistencia> reportes;
        if (codigoAlumno == null) {
            reportes = asistenciasDAO.obtenerReporteAsistencias(null, clase.getIdClaseString(), fechaInicio, fechaFin);
        } else {
            Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(codigoAlumno);
            reportes = asistenciasDAO.obtenerReporteAsistencias(alumno.getIdString(), clase.getIdClaseString(), fechaInicio, fechaFin);
        }
        if (reportes.isEmpty()) {
            String mensaje;
            if (codigoAlumno == null) {
                mensaje = "No se encontraron asistencias para la clase: " + clase.getNombre();
            } else {
                Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(codigoAlumno);
                mensaje = "No se encontraron asistencias para el alumno: " + alumno.getNombreCompleto();
            }
            throw new NegocioException(mensaje);
        }

        List<ReporteAsistenciaDTO> reportesDTO = new ArrayList<>();
        for (ReporteAsistencia reporte : reportes) {
            ReporteAsistenciaDTO reporteDTO = new ReporteAsistenciaDTO(
                    reporte.getIdAlumno(),
                    reporte.getNombre(),
                    reporte.getFechaClase(),
                    TipoAsistenciaDTO.valueOf(reporte.getTipoAsistencia().name()),
                    reporte.getJustificante()
            );
            reportesDTO.add(reporteDTO);
        }

        return reportesDTO;
    }

}
