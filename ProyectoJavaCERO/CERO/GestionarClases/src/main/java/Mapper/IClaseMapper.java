/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mapper;

import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.Clase;

/**
 *
 * @author Jack Murrieta
 */
public interface IClaseMapper {
    
    public Clase convertirClaseEntidad(NuevaClaseDTO nuevaClase);
    
    public ClaseListaDTO convertirClaseListaDTO(Clase clase);
    public EditarClaseDTO convertirEditarClase(Clase clase);
    //Metodo para obtener un editarClase y convertirlo en entidad clase
}
