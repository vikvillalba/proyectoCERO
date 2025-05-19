/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GestionarClasesPersistencia;

import Entidades.Clase;
import Entidades.Maestro;
import Excepciones.PersistenciaException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public interface IMaestrosDAO {

    public List<Maestro> obtenerMaestros();

    //metodo buscarEspacioHorario
    public void agregarClaseImpartida(Clase clase);

    public Maestro buscarMaestro(String idMaestro) throws PersistenciaException;
    
}
