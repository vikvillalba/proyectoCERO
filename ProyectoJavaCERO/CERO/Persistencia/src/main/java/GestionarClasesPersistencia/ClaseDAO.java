/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionarClasesPersistencia;

import ConexionBD.ConexionMongoBD;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Maestro;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public class ClaseDAO implements IClaseDAO {

    private final MongoCollection<Clase> coleccion;

    public ClaseDAO() {
        MongoDatabase db = ConexionMongoBD.getConexion();
        this.coleccion = db.getCollection("Clase", Clase.class);
    }

    @Override
    public void registrarNuevaClase(Clase nuevaClase) {
        coleccion.insertOne(nuevaClase);
    }

    @Override
    public List<Clase> buscarNombreClases(String nombreClase) {
        return coleccion.find(regex("nombre", ".*" + nombreClase + ".*", "i")).into(new ArrayList<>());
    }

    @Override
    public List<Clase> obtenerClases() {
        return coleccion.find().into(new ArrayList<>());
    }

    @Override
    public void editarClase(Clase editarClase) {
        coleccion.replaceOne(eq("_id", editarClase.getId()), editarClase);
    }

    @Override
    public void eliminarClase(Clase clase) {
        coleccion.deleteOne(eq("_id", clase.getId()));
    }

    @Override
    public List<Clase> obtenerListaClasesMaestro(Maestro maestro) {
        return coleccion.find(eq("maestro.id", maestro.getId())).into(new ArrayList<>());
    }

    @Override
    public List<Clase> obtenerListaClasesAula(AulaClase aula) {
        return coleccion.find(eq("aula.id", aula.getId())).into(new ArrayList<>());
    }

    @Override
    public List<Clase> obtenerClasesPorNombre(String nombreClase) {
        return coleccion.find(eq("nombre", nombreClase)).into(new ArrayList<>());
    }

    @Override
    public Clase buscarClase(Integer codigo) {
        return coleccion.find(eq("codigo", codigo)).first();
    }

    @Override
    public Integer obtenerLimiteFaltas(Clase clase) {
        Clase claseEncontrada = coleccion.find(eq("_id", clase.getId())).first();
        return (claseEncontrada != null) ? claseEncontrada.getLIMITE_FALTAS() : null;
    }
}
