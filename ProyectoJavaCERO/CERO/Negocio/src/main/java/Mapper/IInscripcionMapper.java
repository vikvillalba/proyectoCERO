/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mapper;

import DTOs.GestionarClases.AlumnoInscritoDTO;
import Entidades.Inscripcion;

/**
 *
 * @author Jack Murrieta
 */
public interface IInscripcionMapper {
    public AlumnoInscritoDTO convertirAlumnoInscritoDTO(Inscripcion inscripcion);
    
}
