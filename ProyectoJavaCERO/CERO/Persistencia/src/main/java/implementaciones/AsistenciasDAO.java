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
import com.mongodb.client.result.UpdateResult;
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
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection(COLECCION, Asistencia.class);

        LocalDateTime inicioDia = fechaClase.atStartOfDay();
        LocalDateTime finDia = fechaClase.atTime(LocalTime.MAX);

        Document filtro = new Document("clase", clase.getId())
                .append("fechaHora", new Document("$gte", inicioDia).append("$lte", finDia));

        List<Asistencia> asistenciasClase = coleccion.find(filtro).into(new ArrayList<>());

        return asistenciasClase;

    }

    @Override
    public Asistencia justificarFalta(Asistencia faltaJustificada) {
        if (faltaJustificada.getId() == null) {
            return null;
        }
        System.out.println("Justificante: " + faltaJustificada.getJustificante().getMotivo());

        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection(COLECCION, Asistencia.class);

        Document filtro = new Document("_id", faltaJustificada.getId());

        Document update = new Document("$set", new Document("tipoAsistencia", faltaJustificada.getTipoAsistencia())
                .append("justificante", faltaJustificada.getJustificante()));

        UpdateResult resultado = coleccion.updateOne(filtro, update);
        return faltaJustificada;
    }

    @Override
    public List<Asistencia> obtenerFaltasJustificadasAlumnoClase(Alumno alumno, Clase clase) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection(COLECCION, Asistencia.class);

        Document filtro = new Document();
        filtro.append("alumno", alumno.getId());
        filtro.append("clase", clase.getId());
        filtro.append("tipoAsistencia", TipoAsistencia.JUSTIFICADO.name());

        List<Asistencia> asistenciasClase = coleccion.find(filtro).into(new ArrayList<>());
        return asistenciasClase;

    }

    @Override
    public List<Asistencia> actualizarAsistencias(List<Asistencia> nuevasAsistencias, String idClase) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Asistencia> coleccion = baseDatos.getCollection(COLECCION, Asistencia.class);

        Document filtros = new Document("clase", new ObjectId(idClase));
        List<Asistencia> asistenciasRegistradas = coleccion.find(filtros).into(new ArrayList<>());

        List<ObjectId> idsRegistrados = new ArrayList<>();
        for (Asistencia asistencia : asistenciasRegistradas) {
            if (asistencia.getId() != null) {
                idsRegistrados.add(asistencia.getId());
            }
        }

        for (Asistencia nueva : nuevasAsistencias) {
            if (nueva.getId() != null && idsRegistrados.contains(nueva.getId())) {
                // reemplaza si existe
                Document filtro = new Document();
                filtro.append("alumno", nueva.getAlumno());
                filtro.append("clase", nueva.getClase());
                Asistencia asistenciaExistente = coleccion.find(filtro).first();

                if (asistenciaExistente != null) {
                    ObjectId idExistente = asistenciaExistente.getId();
                    Document filtroId = new Document("_id", idExistente);
                    nueva.setId(idExistente);
                    ReplaceOptions opciones = new ReplaceOptions().upsert(true);
                    coleccion.replaceOne(filtroId, nueva, opciones);
                } else {

                    coleccion.insertOne(nueva);
                }

            }

        }
        return nuevasAsistencias;
    }

    @Override
    public List<ReporteAsistencia> obtenerReporteAsistencias(String idAlumno, String idClase, LocalDate fechaInicio, LocalDate fechaFin) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Document> coleccion = baseDatos.getCollection("Asistencias");

        Date fechaInicioDate = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFinDate = Date.from(fechaFin.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

        List<Bson> filtros = new ArrayList<>();

        filtros.add(Filters.eq("clase", new ObjectId(idClase)));

        if (idAlumno != null && !idAlumno.trim().isEmpty()) {
            filtros.add(Filters.eq("alumno", new ObjectId(idAlumno)));
        }

        filtros.add(Filters.gte("fechaHora", fechaInicioDate));
        filtros.add(Filters.lte("fechaHora", fechaFinDate));

        Bson filtroFinal = Filters.and(filtros);

        List<Bson> pipeline = Arrays.asList(
                Aggregates.match(filtroFinal),
                Aggregates.lookup("Alumnos", "alumno", "_id", "datosAlumno"),
                Aggregates.unwind("$datosAlumno")
        );

        List<ReporteAsistencia> reportes = new ArrayList<>();

        for (Document documento : coleccion.aggregate(pipeline)) {
            Document alumno = documento.get("datosAlumno", Document.class);
            String nombre = alumno.getString("nombre") + " " + alumno.getString("apellidoPaterno");
            Integer codigo = alumno.getInteger("codigo");

            Date fecha = documento.getDate("fechaHora");
            LocalDate fechaClase = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            String tipoAsistenciaStr = documento.getString("tipoAsistencia");
            TipoAsistencia tipoEnum = TipoAsistencia.valueOf(tipoAsistenciaStr.toUpperCase());

            String justificante = "No aplica";
            if (documento.containsKey("justificante")) {
                Document justificanteDoc = documento.get("justificante", Document.class);
                if (justificanteDoc != null) {
                    justificante = justificanteDoc.getString("descripcion") != null
                            ? justificanteDoc.getString("descripcion")
                            : justificanteDoc.getString("motivo");
                }
            }

            reportes.add(new ReporteAsistencia(codigo, nombre, fechaClase, tipoEnum, justificante));
        }

        return reportes;
    }

}
