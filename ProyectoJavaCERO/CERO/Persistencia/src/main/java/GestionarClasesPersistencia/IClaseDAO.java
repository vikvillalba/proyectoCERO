/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GestionarClasesPersistencia;

import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Maestro;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public interface IClaseDAO {

    public List<Clase> buscarNombreClases(String nombreClase);

    public List<Clase> obtenerClases();

    public void registrarNuevaClase(Clase nuevaClase);

    public void editarClase(Clase editarClase);

    public void eliminarClase(Clase clase);

    public List<Clase> obtenerListaClasesMaestro(Maestro maestro);

    public List<Clase> obtenerListaClasesAula(AulaClase aula);

    //Metodos para CU_INscribirClase
    public List<Clase> obtenerClasesPorNombre(String nombreClase);

    public Clase buscarClase(ObjectId id);

    public Integer obtenerLimiteFaltas(Clase clase);

}
