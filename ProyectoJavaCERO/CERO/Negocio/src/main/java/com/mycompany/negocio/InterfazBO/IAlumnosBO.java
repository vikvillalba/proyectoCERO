package com.mycompany.negocio.InterfazBO;

import com.mycompany.dtos.AlumnoDTO;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IAlumnosBO {
    public List<AlumnoDTO> obtenerAlumnos();
    public AlumnoDTO agregarAlumno(AlumnoDTO alumnoDTO);
    
}
