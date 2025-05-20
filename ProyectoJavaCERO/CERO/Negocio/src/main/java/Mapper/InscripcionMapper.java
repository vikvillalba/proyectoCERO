/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.GestionarClases.AlumnoClaseDTO;
import DTOs.GestionarClases.ClaseListaDTO;
import Entidades.Alumno;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Inscripcion;
import Entidades.Maestro;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionClaseDTO;
import com.mycompany.dtos.InscripcionDTO;

/**
 *
 * @author Jack Murrieta
 */
public class InscripcionMapper implements IInscripcionMapper{
    
    private ClaseMapper claseMapper;

    public InscripcionMapper() {
        this.claseMapper = new ClaseMapper();
    }

    
    @Override
    public AlumnoClaseDTO convertirAlumnoInscritoDTO(Inscripcion inscripcion) {
        //agregarle el numero de lista
        return null;
        
    }

    @Override
    public InscripcionClaseDTO convertirInscripcionDTO(Inscripcion inscripcion,Alumno alumno, Clase clase, Maestro maestro , AulaClase aula) {
        ClaseListaDTO claseListaDTO = claseMapper.convertirClaseListaDTO(clase, maestro, aula);
        AlumnoDTO alumnoDTO = new AlumnoDTO();
        alumnoDTO.setCodigo(alumno.getCodigo());
        alumnoDTO.setId(alumno.getIdString());
        alumno.setNombre(alumno.getNombre());
        alumno.setApellidoPaterno(alumno.getApellidoPaterno());
        alumno.setApellidoMaterno(alumno.getApellidoMaterno());
        
        return new InscripcionClaseDTO(inscripcion.getIdString(),alumnoDTO, claseListaDTO, inscripcion.isActivo());
    
    }
    
}
