/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfazDAOs;

import Entidades.InscripcionEntidad;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IinscripcionDAO {
    public List<InscripcionEntidad> obtenerInscripcionesClase(Long idClase);
    
    public InscripcionEntidad generarInscripcion(Long idAlumno, Long idClase);
    
}
