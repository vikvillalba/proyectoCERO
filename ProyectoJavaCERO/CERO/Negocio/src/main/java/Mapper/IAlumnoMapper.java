/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mapper;

import Entidades.Alumno;
import com.mycompany.dtos.AlumnoDTO;

/**
 *
 * @author Jack Murrieta
 */
public interface IAlumnoMapper {
    
    public AlumnoDTO convertirAlumnoDTO(Alumno alumno);
    
}
