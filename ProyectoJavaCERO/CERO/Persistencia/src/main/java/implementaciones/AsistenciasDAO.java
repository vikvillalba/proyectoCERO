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

}
