
package com.mycompany.negocio.BOs;

import DAOs.IAlumnosDAO;
import Entidades.Alumno;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.negocio.InterfazBO.IAlumnosBO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victoria
 */
public class AlumnosBO implements IAlumnosBO{
    private IAlumnosDAO alumnosDAO;

    public AlumnosBO(IAlumnosDAO alumnosDAO) {
        this.alumnosDAO = alumnosDAO;
    }

    @Override
    public List<AlumnoDTO> obtenerAlumnos() {
        List<Alumno> alumnosObtenidos = alumnosDAO.obtenerAlumnos();
        List<AlumnoDTO> alumnos = new ArrayList<>();
                
        for (Alumno alumno : alumnosObtenidos) {
            AlumnoDTO alumnoDTO = new AlumnoDTO(
                    alumno.getCodigo(), 
                    alumno.getApellidoPaterno(), 
                    alumno.getApellidoMaterno(), 
                    alumno.getNombre(), 
                    alumno.getTelefono(), 
                    alumno.getFechaNacimiento(), 
                    alumno.getCorreoElectronico()
            );
            
            alumnos.add(alumnoDTO);
        }
        
        return alumnos;
    }

    @Override
    public AlumnoDTO agregarAlumno(AlumnoDTO alumnoDTO) {
        Alumno alumno = new Alumno(
                alumnoDTO.getApellidoPaterno(), 
                alumnoDTO.getApellidoMaterno(), 
                alumnoDTO.getNombre(), 
                alumnoDTO.getTelefono(), 
                alumnoDTO.getFechaNacimiento(), 
                alumnoDTO.getCorreoElectronico()
        );
        
        Alumno alumnoRegistrado = alumnosDAO.registrarAlumnoNuevo(alumno);
        
        AlumnoDTO alumnoRegistradoDTO = new AlumnoDTO(
                alumnoRegistrado.getCodigo(), 
                alumnoRegistrado.getApellidoPaterno(), 
                alumnoRegistrado.getApellidoMaterno(), 
                alumnoRegistrado.getNombre(), 
                alumnoRegistrado.getTelefono(), 
                alumnoRegistrado.getFechaNacimiento(), 
                alumnoRegistrado.getCorreoElectronico()
        );
        
        return alumnoRegistradoDTO;
    }


    
    
    
}
