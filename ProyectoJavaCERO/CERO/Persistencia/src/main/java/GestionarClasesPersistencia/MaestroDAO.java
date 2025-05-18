/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionarClasesPersistencia;

import ConexionBD.ConexionMongoBD;
import Entidades.Clase;
import Entidades.Maestro;
import Excepciones.PersistenciaException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.addToSet;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public class MaestroDAO implements IMaestroDAO {

    private final MongoCollection<Maestro> coleccionMaestros;

    public MaestroDAO() {
        MongoDatabase db = ConexionMongoBD.getConexion();
        this.coleccionMaestros = db.getCollection("Maestro", Maestro.class);
    }

    @Override
    public List<Maestro> obtenerMaestros() {
        List<Maestro> maestros = new ArrayList<>();
        FindIterable<Maestro> resultado = coleccionMaestros.find();

        for (Maestro maestro : resultado) {
            maestros.add(maestro);
        }

        return maestros;
    }

    @Override
    public void agregarClaseImpartida(Clase clase) {
        ObjectId idClase = clase.getId();
        ObjectId idMaestro = clase.getMaestro() != null ? clase.getMaestro().getId() : null;

        if (idClase == null || idMaestro == null) {
            throw new IllegalArgumentException("Clase o maestro no tiene un ID válido.");
        }

        // Agrega el ObjectId de la clase al arreglo clasesImpartidas del maestro, sin duplicados
        coleccionMaestros.updateOne(
                eq("_id", idMaestro),
                addToSet("clasesImpartidas", idClase)
        );
    }

    @Override
    public Maestro buscarMaestro(Maestro maestro) throws PersistenciaException {
        if (maestro == null || maestro.getId() == null) {
            throw new IllegalArgumentException("El maestro o su ID no pueden ser nulos");
        }

        Maestro resultado = coleccionMaestros.find(eq("_id", maestro.getId())).first();

        if (resultado == null) {
            throw new PersistenciaException("No se encontró¿o el maestr@ con el ID especificado: " + maestro.getId().toHexString());
        }

        return resultado;
    }
}
