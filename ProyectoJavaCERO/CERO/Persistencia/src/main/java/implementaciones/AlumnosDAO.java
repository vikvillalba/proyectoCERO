package implementaciones;

import ConexionBD.ConexionMongoBD;
import Entidades.Alumno;
import DAOs.IAlumnosDAO;
import Entidades.Contador;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
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
        MongoCollection<Alumno> coleccion = baseDatos.getCollection(COLECCION, Alumno.class);

        return coleccion.find().into(new ArrayList<>());
    }

    @Override
    public Alumno obtenerAlumnoPorCodigo(Integer codigo) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Alumno> coleccion = baseDatos.getCollection(COLECCION, Alumno.class);

        return coleccion.find(eq("codigo", codigo)).first();
    }

    @Override
    public Alumno editarAlumno(Alumno alumno) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Alumno> coleccion = baseDatos.getCollection(COLECCION, Alumno.class);

        coleccion.updateOne(
                eq("_id", alumno.getId()),
                Updates.combine(
                        Updates.set("apellidoPaterno", alumno.getApellidoPaterno()),
                        Updates.set("apellidoMaterno", alumno.getApellidoMaterno()),
                        Updates.set("nombre", alumno.getNombre()),
                        Updates.set("telefono", alumno.getTelefono()),
                        Updates.set("correoElectronico", alumno.getCorreoElectronico()),
                        Updates.set("fechaNacimiento", alumno.getFechaNacimiento()),
                        Updates.set("codigo", alumno.getCodigo())
                )
        );

        return alumno;
    }

    @Override
    public void eliminarAlumno(Integer codigo) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Alumno> coleccion = baseDatos.getCollection(COLECCION, Alumno.class);

        coleccion.deleteOne(eq("codigo", codigo));
    }
}
