
package com.mycompany.registroasistencias;

import com.mycompany.dtos.AlumnoBusquedaDTO;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.AsistenciaDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NuevaAsistenciaDTO;
import com.mycompany.registroasistencias.excepciones.AsistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones para registrar asistencias, faltas o justificantes.
 * @author victoria
 */
public interface IRegistroAsistencias {
    public List<InscripcionDTO> obtenerInscripcionesAlumno(AlumnoDTO alumno) throws AsistenciaException;
    public AlumnoDTO obtenerAlumno(AlumnoBusquedaDTO alumnoBusqueda);
    public AsistenciaDTO registrarAsistenciaIndividual(NuevaAsistenciaDTO nuevaAsistencia) throws AsistenciaException;
    public AsistenciaDTO obtenerAsistenciasAlumnoClase(AlumnoDTO alumno, ClaseDTO clase);
}
