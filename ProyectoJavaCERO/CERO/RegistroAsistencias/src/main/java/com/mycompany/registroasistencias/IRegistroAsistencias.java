package com.mycompany.registroasistencias;

import com.mycompany.dtos.AlumnoBusquedaDTO;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.AsistenciaDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NuevaAsistenciaDTO;
import com.mycompany.registroasistencias.excepciones.AsistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz que define las operaciones para registrar asistencias, faltas o justificantes.
 *
 * @author victoria
 */
public interface IRegistroAsistencias {

    /**
     * Obtiene todas las inscripciones de un alumno en específico
     *
     * @param alumno el alumno seleccionado.
     * @return lista con las inscripciones empacadas en un objeto DTO.
     * @throws com.mycompany.registroasistencias.excepciones.AsistenciaException
     */
    public List<InscripcionDTO> obtenerInscripcionesAlumno(AlumnoDTO alumno) throws AsistenciaException;

    /**
     * Obtiene un alumno registrado a partir de un parámetro de búsqueda
     *
     * @param alumnoBusqueda datos de búsqueda.
     * @return datos del alumno registrado empacados en un objeto DTO.
     */
    public AlumnoDTO obtenerAlumno(AlumnoBusquedaDTO alumnoBusqueda);

    /**
     * Registra una asistencia individual para un alumno en una clase específica
     *
     * @param nuevaAsistencia datos de la nueva asistencia.
     * @return datos de la asistencia ya que se registró en el sistema.
     * @throws com.mycompany.registroasistencias.excepciones.AsistenciaException
     */
    public AsistenciaDTO registrarAsistenciaIndividual(NuevaAsistenciaDTO nuevaAsistencia) throws AsistenciaException;

    /**
     * Obtiene una asistencia de un alumno específico en una clase específica. se utiliza para validar si el alumno ya tiene asistencia registrada, tomando en cuenta la clase y la fecha.
     *
     * @param alumno alumno al que se le busca la asistencia.
     * @param clase en la que se desea validar.
     * @return datos de la asistencia en caso de existir, nulo en caso contrario.
     */
    public AsistenciaDTO obtenerAsistenciaAlumnoClase(AlumnoDTO alumno, ClaseDTO clase);

    /**
     * Obtiene las clases coincidentes con el parámetro de búsqueda
     *
     * @param nombre nombre total o parcial de la clase con la que se filtran los resultados.
     * @return lista con las clases coincidentes.
     * @throws com.mycompany.registroasistencias.excepciones.AsistenciaException
     */
    public List<ClaseDTO> obtenerClasesNombre(String nombre) throws AsistenciaException;

    /**
     * Valida que el parámetro de búsqueda de la clase no esté vacío
     *
     * @param nombre nombre total o parcial de la clase que se está buscando.
     * @return true si está vacío, false de caso contrario.
     */
    public boolean validarNombreClaseVacio(String nombre);

    /**
     * Valida que el parámetro de búsqueda corresponda a una o más clases registradas en el sistema.
     *
     * @param nombre nombre total o parcial de la clase que se está buscando.
     * @return true si hay mínimo una clase que coincide, false de caso contrario.
     * @throws com.mycompany.registroasistencias.excepciones.AsistenciaException
     */
    public boolean validarNombreClase(String nombre) throws AsistenciaException;

    /**
     * Obtiene todos los días en los que se imparte una clase, desde la fecha de inicio hasta la fecha actual.
     *
     * @param clase sobre la que se está trabajando.
     * @return una lista con las fechas de cada sesión de clase.
     */
    public List<LocalDate> obtenerDiasClase(ClaseDTO clase);

    public List<AsistenciaDTO> obtenerAsistenciasClase(ClaseDTO clase, LocalDate diaClase) throws AsistenciaException;

    public AsistenciaDTO justificarFalta(AsistenciaDTO faltaJustificada) throws AsistenciaException;

    public AsistenciaDTO validarFaltasJustificadasAlumno(AsistenciaDTO asistencia) throws AsistenciaException;

    public List<InscripcionDTO> obtenerInscripcionesClase(ClaseDTO clase) throws AsistenciaException;

    public List<AsistenciaDTO> actualizarAsistencias(List<AsistenciaDTO> asistencias) throws AsistenciaException;
}
