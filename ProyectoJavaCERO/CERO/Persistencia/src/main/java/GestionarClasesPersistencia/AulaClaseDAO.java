/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionarClasesPersistencia;

import ConexionBD.ConexionMongoBD;
import Entidades.AulaClase;
import Entidades.Clase;
import Excepciones.PersistenciaException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public class AulaClaseDAO implements IAulaClaseDAO {

    private final MongoCollection<AulaClase> coleccionAulas;
    private final MongoCollection<Clase> coleccionClases;

    public AulaClaseDAO() {
        MongoDatabase db = ConexionMongoBD.getConexion();
        this.coleccionAulas = db.getCollection("Aulas", AulaClase.class);
        this.coleccionClases = db.getCollection("Clases", Clase.class);
    }

    /**
     * Devuelve la lista de objetos Clase referenciados en clasesPresenciales del aula.
     * @param aula
     * @return 
     */
    @Override
    public List<Clase> obtenerAulaClases(AulaClase aula) {
        List<Clase> clases = new ArrayList<>();
        AulaClase aulaEnBD = coleccionAulas.find(eq("_id", aula.getId())).first();

        if (aulaEnBD != null && aulaEnBD.getClasesPresenciales() != null) {
            for (ObjectId idClase : aulaEnBD.getClasesPresenciales()) {
                Clase clase = coleccionClases.find(eq("_id", idClase)).first();
                if (clase != null) {
                    clases.add(clase);
                }
            }
        }

        return clases;
    }

    /**
     * Agrega el ID de la clase al arreglo clasesPresenciales del aula correspondiente.
     *
     * @param clase
     */
    @Override
    public void agregarClasePresencial(Clase clase) throws PersistenciaException {
        ObjectId idClase = clase.getId();
        ObjectId idAula = clase.getIdAula();  // ✅ uso del nuevo campo idAula

        if (idClase == null || idAula == null) {
            throw new PersistenciaException("Clase o aula no tiene un ID válido.");
        }

        // Agrega el ID de la clase al arreglo clasesPresenciales del aula, sin duplicados
        coleccionAulas.updateOne(
                eq("_id", idAula),
                addToSet("clasesPresenciales", idClase)
        );
    }

    @Override
    public List<AulaClase> obtenerAulas() {
        List<AulaClase> aulas = new ArrayList<>();
        FindIterable<AulaClase> rsultado = coleccionAulas.find();
        for (AulaClase aulaClase : rsultado) {
            aulas.add(aulaClase);
            
        }
        return aulas;
    }

    public AulaClase buscarClase(ObjectId idAula) throws PersistenciaException {
        if (idAula == null) {
            throw new IllegalArgumentException("El ID del aula no puede ser nulo");
        }

        AulaClase resultado = coleccionAulas.find(eq("_id", idAula)).first();

        if (resultado == null) {
            throw new PersistenciaException("No se encontró el aula con el ID especificado: " + idAula.toHexString());
        }

        return resultado;
    }
}
