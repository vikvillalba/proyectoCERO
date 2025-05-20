/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlGestionarAlumnos;

import Exception.GestionarAlumnosException;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.InscripcionClaseDTO;
import com.mycompany.negocio.Fabricas.FabricaObjetosNegocio;
import com.mycompany.negocio.InterfazBO.IAlumnosBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.List;


/**
 *
 * @author Jack Murrieta
 */
public class ControlGestionarAlumnos implements IControlGestionarAlumnos{
    
    private IAlumnosBO alumnosBO;

    public ControlGestionarAlumnos() {
        this.alumnosBO = FabricaObjetosNegocio.obtenerAlumnosBO();
    }
    
    @Override
    public AlumnoDTO registrarNuevoAlumno(AlumnoDTO nuevoAlumno) {
        //valida los datos del alumno
        //valida la fecha que no sea la actual
        //valida el nombre que sea solo letras
        //valida que el correo tenga @
        //valida que el telefono sean numeros
        //valida que los campos no sean muy extensos
        return alumnosBO.agregarAlumno(nuevoAlumno);
    
    }

    @Override
    public void eliminarAlumno(AlumnoDTO alumnoDTO) {
        alumnosBO.eliminarAlumno(alumnoDTO);
    
    }

    @Override
    public void editarAlumno(AlumnoDTO alumnoDTO) {
        //valida los datos del alumno
        //valida la fech que no sea actual
        alumnosBO.editarAlumno(alumnoDTO);
    
    }

    @Override
    public List<InscripcionClaseDTO> obtenerInscripciones(AlumnoDTO alumnoDTO) throws GestionarAlumnosException {
        try {
            return alumnosBO.obtenerInscripciones(alumnoDTO);
        } catch (NegocioException ex) {
            throw new GestionarAlumnosException(ex.getMessage());
        }
    }

    @Override
    public List<AlumnoDTO> obtenerAlumnosDTOLista() {
        return alumnosBO.obtenerAlumnosDTOLista();
    }
    
}
