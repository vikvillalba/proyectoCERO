/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfazDAOs;

import Entidades.ClaseEntidad;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IClaseDAO {
    
    public List<ClaseEntidad> obtenerClasesPorNombre(String nombreClase);
    
    public ClaseEntidad buscarClase(Long id);
    
}
