package implementaciones;

import ConexionBD.ConexionMongoBD;
import Entidades.Alumno;
import java.time.LocalDate;
import DAOs.IAlumnosDAO;
import Entidades.Contador;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */
public class AlumnosDAO implements IAlumnosDAO {

    private final String COLECCION = "Alumnos";
    private final String CAMPO_ID = "_id";

    public AlumnosDAO() {
    }

    @Override
    public Alumno obtenerAlumno(String idAlumno) {

        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Alumno> coleccion = baseDatos.getCollection(COLECCION, Alumno.class);

        // select * from Alumnos where _id = "id";
        Document filtros = new Document();
        filtros.append(CAMPO_ID, new ObjectId(idAlumno));

        FindIterable<Alumno> resultado = coleccion.find(filtros);
        Alumno Alumno = resultado.first();

        return Alumno;
    }

    @Override
    public Alumno registrarAlumnoNuevo(Alumno alumno) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Alumno> coleccion = baseDatos.getCollection(COLECCION, Alumno.class);
        alumno.setCodigo(obtenerSiguienteCodigo());
        
        coleccion.insertOne(alumno);
        return alumno;
    }

    private Integer obtenerSiguienteCodigo() {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Contador> coleccion = baseDatos.getCollection("counters", Contador.class);

        Contador actualizado = coleccion.findOneAndUpdate(
                Filters.eq("_id", "alumno"),
                Updates.inc("codigoSecuencia", 1),
                new FindOneAndUpdateOptions()
                        .upsert(true)
                        .returnDocument(ReturnDocument.AFTER)
        );

        return actualizado.getCodigoSecuencia();
    }

    @Override
    public List<Alumno> obtenerAlumnos() {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Alumno> coleccion = baseDatos.getCollection("Alumnos", Alumno.class);

        return coleccion.find().into(new ArrayList<>());
    }

}
