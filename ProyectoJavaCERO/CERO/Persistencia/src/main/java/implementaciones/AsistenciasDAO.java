package implementaciones;

import ConexionBD.ConexionMongoBD;
import Entidades.Alumno;
import Entidades.Asistencia;
import Entidades.Clase;
import Entidades.TipoAsistencia;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import DAOs.IAsistenciasDAO;
import Entidades.ReporteAsistencia;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Implementación de la interfaz IAsistenciasDAO
 *
 * @author victoria
 */
public class AsistenciasDAO implements IAsistenciasDAO {

    private final String COLECCION = "Asistencias";

    @Override
    public Asistencia registrarAsistencia(Asistencia asistencia) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection(COLECCION, Asistencia.class);
        coleccion.insertOne(asistencia);
        return asistencia;
    }

    @Override
    public Asistencia obtenerAsistenciaAlumnoClase(Alumno alumno, Clase clase) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection(COLECCION, Asistencia.class);

        Document filtros = new Document();
        filtros.append("alumno", alumno.getId());
        filtros.append("clase", clase.getId());

        LocalDate hoy = LocalDate.now();
        LocalDateTime inicioDia = hoy.atStartOfDay();
        LocalDateTime finDia = hoy.atTime(LocalTime.MAX);

        filtros.append("fechaHora", new Document("$gte", inicioDia).append("$lte", finDia));

        Asistencia asistencia = coleccion.find(filtros).first();
        return asistencia;
    }

    @Override
    public List<Asistencia> obtenerAsistenciasAlumnos(Clase clase, LocalDate fechaClase) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection("asistencias", Asistencia.class);

        LocalDateTime inicioDia = fechaClase.atStartOfDay();
        LocalDateTime finDia = fechaClase.atTime(LocalTime.MAX);

        Document filtro = new Document("clase", clase.getId())
                .append("fechaHora", new Document("$gte", inicioDia).append("$lte", finDia));

        List<Asistencia> asistenciasClase = coleccion.find(filtro).into(new ArrayList<>());

        return asistenciasClase;

    }

    @Override
    public Asistencia justificarFalta(Asistencia faltaJustificada) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection("asistencias", Asistencia.class);

        if (faltaJustificada.getId() == null) {
            return null;
        }

        Document filtro = new Document("_id", faltaJustificada.getId());

        ReplaceOptions opciones = new ReplaceOptions().upsert(false);
        coleccion.replaceOne(filtro, faltaJustificada, opciones);

        return faltaJustificada;
    }

    @Override
    public List<Asistencia> obtenerFaltasJustificadasAlumnoClase(Alumno alumno, Clase clase) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection("asistencias", Asistencia.class);

        Document filtro = new Document();
        filtro.append("alumno", alumno.getId());
        filtro.append("clase", clase.getId());
        filtro.append("tipoAsistencia", TipoAsistencia.JUSTIFICADO);

        List<Asistencia> asistenciasClase = coleccion.find(filtro).into(new ArrayList<>());
        return asistenciasClase;

    }

    @Override
    public List<Asistencia> actualizarAsistencias(List<Asistencia> nuevasAsistencias) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection("asistencias", Asistencia.class);

        for (Asistencia nueva : nuevasAsistencias) {
            if (nueva.getId() != null) {
                Document filtro = new Document("_id", nueva.getId());
                ReplaceOptions opciones = new ReplaceOptions().upsert(true);
                coleccion.replaceOne(filtro, nueva, opciones);
            } else {
                coleccion.insertOne(nueva);
            }
        }

        return nuevasAsistencias;
    }

    @Override
    public List<ReporteAsistencia> obtenerReporteAsistencias(String idAlumno, String idClase, LocalDate fechaInicio, LocalDate fechaFin) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Document> coleccion = baseDatos.getCollection("asistencias");

        List<Bson> filtros = Arrays.asList(
                Aggregates.match(Filters.and(
                        Filters.eq("alumno", new ObjectId(idAlumno)),
                        Filters.eq("clase", new ObjectId(idClase)),
                        Filters.gte("fechaHora", fechaInicio.atStartOfDay()),
                        Filters.lte("fechaHora", fechaFin.atTime(23, 59, 59))
                )),
                Aggregates.lookup("Alumnos", "alumno", "_id", "datosAlumno"),
                Aggregates.unwind("$datosAlumno")
        );

        List<ReporteAsistencia> reportes = new ArrayList<>();

        for (Document documento : coleccion.aggregate(filtros)) {
            Document alumno = documento.get("datosAlumno", Document.class);
            // obtener datos del alumno
            String nombre = alumno.getString("nombre") + " " + alumno.getString("apellidoPaterno");
            Integer codigo = alumno.getInteger("codigo");
            Date fecha = documento.getDate("fechaHora");
            LocalDate fechaClase = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String tipoAsistencia = documento.get("tipoAsistencia", Document.class).getString("nombre");
            TipoAsistencia tipoEnum = TipoAsistencia.valueOf(tipoAsistencia.toUpperCase());

            Document justificanteDoc = documento.get("justificante", Document.class);

            String justificante = "No aplica";
            if (justificanteDoc != null) {
                justificante = justificanteDoc.getString("descripcion");
            }

            reportes.add(new ReporteAsistencia(codigo, nombre, fechaClase, tipoEnum, justificante));

        }

        return reportes;
    }

}
