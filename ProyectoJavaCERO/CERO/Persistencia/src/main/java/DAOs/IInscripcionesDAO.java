/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Entidades.Inscripcion;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IInscripcionesDAO {
    public List<Inscripcion> obtenerInscripcionesClase(Integer idClase);
    
    public Inscripcion generarInscripcion(Integer idAlumno, Integer idClase);
    
}
