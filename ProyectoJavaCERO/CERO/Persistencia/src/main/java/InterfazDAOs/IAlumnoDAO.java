/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfazDAOs;

import Entidades.AlumnoEntidad;

/**
 *
 * @author Usuario
 */
public interface IAlumnoDAO {
    
    public AlumnoEntidad obtenerAlumno(Long idAlumno);
    public AlumnoEntidad registrarAlumnoNuevo(AlumnoEntidad alumno);

}
