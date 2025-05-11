package com.mycompany.negocio.InterfazBO;

import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.AsistenciaDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.NuevaAsistenciaDTO;
import com.mycompany.negocio.excepciones.NegocioException;

/**
 * Interfaz que establece las operaciones de negocio para las asistencias.
 * @author victoria
 */
public interface IAsistenciasBO {
    public AsistenciaDTO registrarAsistencia(NuevaAsistenciaDTO nuevaAsistencia)throws NegocioException;
    public AsistenciaDTO obtenerAsistenciaAlumnoClase(AlumnoDTO alumno, ClaseDTO clase);
}
