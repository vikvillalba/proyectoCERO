package implementaciones;

import ConexionBD.ConexionMongoBD;
import Entidades.Alumno;
import Entidades.Inscripcion;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import Entidades.Clase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.types.ObjectId;
import DAOs.IInscripcionesDAO;

/**
 *
 * @author Usuario
 */
public class InscripcionesDAO implements IInscripcionesDAO {

    private final String COLECCION = "Inscripciones";

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
        MongoCollection<Inscripcion> coleccion = baseDatos.getCollection(COLECCION, Inscripcion.class);

        coleccion.insertOne(inscripcion);
        inscripcion.getPago().setRealizado(true);
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

        List<ObjectId> clasesConDiaActual = baseDatos.getCollection("clases", Clase.class)
                .find(Filters.in("dias", diaActual.name()))
                .map(Clase::getId)
                .into(new ArrayList<>());

        if (clasesConDiaActual.isEmpty()) {
            return new ArrayList<>();
        }

        return coleccion.find(
                Filters.and(
                        Filters.eq("alumno", alumno.getId()),
                        Filters.in("clase", clasesConDiaActual)
                )
        ).into(new ArrayList<>());
    }

}
