package implementaciones;

import ConexionBD.ConexionMongoBD;
import Entidades.Alumno;
import Entidades.Inscripcion;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.types.ObjectId;
import DAOs.IInscripcionesDAO;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import org.bson.conversions.Bson;

/**
 *
 * @author Jack Murrieta
 */
public class InscripcionesDAO implements IInscripcionesDAO {

    private final String COLECCION = "Inscripciones";
    private final String COLECCION_ALUMNOS = "Alumnos";

    @Override
    public List<Inscripcion> obtenerInscripcionesClase(String idClase) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Inscripcion> coleccion = baseDatos.getCollection(COLECCION, Inscripcion.class);

        Document filtro = new Document();
        filtro.append("clase", new ObjectId(idClase));

        List<Inscripcion> inscripcionesClase = new ArrayList<>();
        FindIterable<Inscripcion> resultados = coleccion.find(filtro);

        for (Inscripcion inscripcion : resultados) {
            inscripcionesClase.add(inscripcion);
        }

        return inscripcionesClase;
    }

    @Override
    public Inscripcion registrarInscripcion(Inscripcion inscripcion) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();

        // Colecciones
        MongoCollection<Inscripcion> coleccionInscripciones = baseDatos.getCollection(COLECCION, Inscripcion.class);
        MongoCollection<Alumno> coleccionAlumnos = baseDatos.getCollection(COLECCION_ALUMNOS, Alumno.class);

        // Marcar el pago como realizado
        inscripcion.getPago().setRealizado(true);

        // Insertar la inscripción
        inscripcion.setActivo(true);
        coleccionInscripciones.insertOne(inscripcion);
        ObjectId idInscripcion = inscripcion.getId(); 

        // Actualizar el alumno agregando la inscripción a su lista
        coleccionAlumnos.updateOne(
                eq("_id", inscripcion.getAlumno()),
                Updates.push("inscripcionesAlumno", idInscripcion)
        );

        return inscripcion;

    }

    @Override
    public List<Inscripcion> obtenerInscripcionesAlumno(Alumno alumno) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Inscripcion> coleccion = baseDatos.getCollection(COLECCION, Inscripcion.class);

        Document filtro = new Document();
        filtro.append("alumno", alumno.getId());

        List<Inscripcion> inscripcionesClase = coleccion.find(filtro).into(new ArrayList<>());

        return inscripcionesClase;
    }

    @Override
    public List<Inscripcion> obtenerInscripcionesAlumnoDiaActual(Alumno alumno) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Inscripcion> coleccion = baseDatos.getCollection(COLECCION, Inscripcion.class);

        DayOfWeek diaActual = LocalDate.now().getDayOfWeek();

        List<Inscripcion> resultado = coleccion.aggregate(Arrays.asList(
                Aggregates.match(Filters.eq("alumno", alumno.getId())),
                Aggregates.lookup("Clases", "clase", "_id", "infoClase"),
                Aggregates.unwind("$infoClase"),
                Aggregates.match(Filters.in("infoClase.dias", diaActual.name()))
        )).into(new ArrayList<>());

        return resultado;
    }

    @Override
    public List<Alumno> obtenerAlumnosInscritosClase(String idClase) {
        MongoDatabase db = ConexionMongoBD.getConexion();
        MongoCollection<Document> coleccion = db.getCollection("Inscripciones");

        List<Bson> pipeline = Arrays.asList(
                Aggregates.match(Filters.eq("clase", new ObjectId(idClase))),
                Aggregates.lookup("Alumnos", "alumno", "_id", "datosAlumno"),
                Aggregates.unwind("$datosAlumno"),
                Aggregates.replaceRoot("$datosAlumno")
        );

        List<Alumno> alumnos = new ArrayList<>();

        for (Document doc : coleccion.aggregate(pipeline)) {
            Alumno alumno = new Alumno();
            alumno.setId(doc.getObjectId("_id"));
            alumno.setCodigo(doc.getInteger("codigo"));
            alumno.setApellidoPaterno(doc.getString("apellidoPaterno"));
            alumno.setApellidoMaterno(doc.getString("apellidoMaterno"));
            alumno.setNombre(doc.getString("nombre"));
            alumno.setTelefono(doc.getString("telefono"));

            Date fechaNacimiento = doc.getDate("fechaNacimiento");
            if (fechaNacimiento != null) {
                alumno.setFechaNacimiento(fechaNacimiento.toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate());
            }

            alumno.setCorreoElectronico(doc.getString("correoElectronico"));

            alumnos.add(alumno);
        }

        return alumnos;
    }

    //DAR DEBAJA UN INSCRIPCION
    @Override
    public void cancelarInscripcion(String idInscripcion) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Inscripcion> coleccion = baseDatos.getCollection("Inscripciones", Inscripcion.class);

        coleccion.updateOne(
                eq("_id", new ObjectId(idInscripcion)),
                Updates.set("activo", false)
        );
    }
    
    //obtener inscripciones no activas en list<Inscripcion> ........

}
