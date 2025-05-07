/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfazDAOs;

import Entidades.Alumno;

/**
 *
 * @author Usuario
 */
public interface IAlumnosDAO {
    
    public Alumno obtenerAlumno(Integer idAlumno);
    public Alumno registrarAlumnoNuevo(Alumno alumno);

}
