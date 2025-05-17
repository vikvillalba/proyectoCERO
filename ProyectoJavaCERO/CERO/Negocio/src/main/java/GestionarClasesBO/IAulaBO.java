/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GestionarClasesBO;

import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.Clase;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public interface IAulaBO {
    
    public boolean validarDisponibilidadHorarioAula(NuevaClaseDTO nuevaClase , List<Clase> clasesPresencialesAula);
    public List<Clase> obtenerListaAulas();
    //metodo para obtener clases en aula 
    
}
