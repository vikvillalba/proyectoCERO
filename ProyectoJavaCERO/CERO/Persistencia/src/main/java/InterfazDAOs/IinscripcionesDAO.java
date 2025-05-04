/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfazDAOs;

import Entidades.Inscripcion;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IInscripcionesDAO {
    public List<Inscripcion> obtenerInscripcionesClase(Long idClase);
    
    public Inscripcion generarInscripcion(Long idAlumno, Long idClase);
    
}
