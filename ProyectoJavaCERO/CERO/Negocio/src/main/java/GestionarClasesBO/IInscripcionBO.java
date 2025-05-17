/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GestionarClasesBO;

import Entidades.Clase;
import Entidades.Inscripcion;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public interface IInscripcionBO {
    public List<Inscripcion> obtenerListaInscritosClase(Clase clase);
    
}
