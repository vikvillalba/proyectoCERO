/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mapper;

import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Maestro;
import com.mycompany.dtos.ClaseDTO;

/**
 *
 * @author Jack Murrieta
 */
public interface IClaseMapper {
    
    public Clase convertirClaseEntidad(NuevaClaseDTO nuevaClase, Maestro maestro, AulaClase aula);
    
    public EditarClaseDTO convertirEditarClase(Clase clase, Maestro maestro, AulaClase aula);
    public ClaseListaDTO convertirClaseListaDTO(Clase clase, Maestro maestro, AulaClase aula);
    public ClaseDTO converitirClaseDTO(Clase clase);
    //Metodo para obtener un editarClase y convertirlo en entidad clase
}
