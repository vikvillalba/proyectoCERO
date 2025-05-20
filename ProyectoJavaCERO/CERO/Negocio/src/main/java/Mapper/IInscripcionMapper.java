/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mapper;

import DTOs.GestionarClases.AlumnoClaseDTO;
import Entidades.Alumno;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Inscripcion;
import Entidades.Maestro;
import com.mycompany.dtos.InscripcionClaseDTO;
import com.mycompany.dtos.InscripcionDTO;

/**
 *
 * @author Jack Murrieta
 */
public interface IInscripcionMapper {
    public AlumnoClaseDTO convertirAlumnoInscritoDTO(Inscripcion inscripcion);
    
    public InscripcionClaseDTO convertirInscripcionDTO(Inscripcion inscricion,Alumno alumno, Clase clase, Maestro maestro , AulaClase aula);
    
}
