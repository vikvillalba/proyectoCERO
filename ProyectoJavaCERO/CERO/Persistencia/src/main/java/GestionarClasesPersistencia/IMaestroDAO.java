/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GestionarClasesPersistencia;

import Entidades.Clase;
import Entidades.Maestro;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public interface IMaestroDAO {
    public List<Maestro> obtenerMaestros();
    //metodo buscarEspacioHorario
    public void agregarClaseImpartida(Clase clase);
    
}
