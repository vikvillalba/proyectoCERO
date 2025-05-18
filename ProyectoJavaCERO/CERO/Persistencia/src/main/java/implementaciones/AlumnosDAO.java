package implementaciones;

import ConexionBD.ConexionMongoBD;
import Entidades.Alumno;
import java.time.LocalDate;
import DAOs.IAlumnosDAO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
        // acceso a la coleccion
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

        return alumno;
    }

    @Override
    public List<Alumno> obtenerAlumnos() {
        return null;
    }

}
