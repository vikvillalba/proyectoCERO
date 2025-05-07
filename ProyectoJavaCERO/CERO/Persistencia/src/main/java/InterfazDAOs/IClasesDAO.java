/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfazDAOs;

import Entidades.Clase;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IClasesDAO {
    
    public List<Clase> obtenerClasesPorNombre(String nombreClase);
    
    public Clase buscarClase(Integer id);
    
    public List<Clase> obtenerClases();
    
}
