package com.mycompany.negocio.InterfazBO;

import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.InscripcionClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IAlumnosBO {
    
    public List<AlumnoDTO> obtenerAlumnos();
    public AlumnoDTO agregarAlumno(AlumnoDTO alumnoDTO);
    public void eliminarAlumno(AlumnoDTO alumnoDTO);
    public void editarAlumno(AlumnoDTO alumnoDTO);
    
    public List<InscripcionClaseDTO> obtenerInscripciones(AlumnoDTO alumnoDTO) throws NegocioException;
    public List<AlumnoDTO> obtenerAlumnosDTOLista();
}
