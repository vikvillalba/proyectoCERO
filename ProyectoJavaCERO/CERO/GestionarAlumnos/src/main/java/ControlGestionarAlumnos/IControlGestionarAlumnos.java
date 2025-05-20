/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ControlGestionarAlumnos;

import Exception.GestionarAlumnosException;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.InscripcionClaseDTO;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public interface IControlGestionarAlumnos {

    public AlumnoDTO registrarNuevoAlumno(AlumnoDTO nuevoAlumno);

    public void eliminarAlumno(AlumnoDTO alumnoDTO);

    public void editarAlumno(AlumnoDTO alumnoDTO);

    public List<InscripcionClaseDTO> obtenerInscripciones(AlumnoDTO alumnoDTO) throws GestionarAlumnosException;

    public List<AlumnoDTO> obtenerAlumnosDTOLista();
}
