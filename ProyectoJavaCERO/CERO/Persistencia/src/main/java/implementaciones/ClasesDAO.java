package implementaciones;

import ConexionBD.ConexionMongoBD;
import DAOs.IClasesDAO;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Contador;
import Entidades.Inscripcion;
import Entidades.Maestro;
import Excepciones.PersistenciaException;
import GestionarClasesPersistencia.AulasClaseDAO;
import GestionarClasesPersistencia.MaestrosDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public class ClasesDAO implements IClasesDAO {

    private final MongoCollection<Clase> coleccion;
    private final AulasClaseDAO aulaClaseDAO;
    private final MaestrosDAO maestroDAO;

    public ClasesDAO() {
        MongoDatabase db = ConexionMongoBD.getConexion();
        this.coleccion = db.getCollection("Clases", Clase.class);
        this.aulaClaseDAO = new AulasClaseDAO();
        this.maestroDAO = new MaestrosDAO();
    }

    @Override
    public void registrarNuevaClase(Clase nuevaClase) {
        // Obtener el código máximo actual
        Integer codigoMaximo = obtenerSiguienteCodigo();
        nuevaClase.setCodigo(codigoMaximo);

        ObjectId idMaestro = nuevaClase.getIdMaestro();
        String modalidad = nuevaClase.getModalidad();

        // Validar existencia del aula SOLO si es presencial
        if ("Presencial".equalsIgnoreCase(modalidad)) {
            ObjectId idAula = nuevaClase.getIdAula();

            if (idAula == null) {
                System.err.println("La clase presencial requiere un aula asignada.");
                return;
            }

            try {
                AulaClase aula = aulaClaseDAO.buscarClase(idAula.toHexString());
                if (aula == null) {
                    System.err.println("El aula especificada no existe.");
                    return;
                }
            } catch (PersistenciaException ex) {
                Logger.getLogger(ClasesDAO.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
        
        // Validar existencia del maestro
        if (idMaestro != null) {
            try {
                Maestro maestro = maestroDAO.buscarMaestro(idMaestro.toHexString());
                if (maestro == null) {
                    System.err.println("El maestro especificado no existe.");
                    return;
                }
            } catch (PersistenciaException ex) {
                Logger.getLogger(ClasesDAO.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }

        // Insertar la nueva clase en la colección
        coleccion.insertOne(nuevaClase);

        // Si es presencial, agregarla al aula
        if ("Presencial".equalsIgnoreCase(modalidad) && nuevaClase.getIdAula() != null) {
            try {
                aulaClaseDAO.agregarClasePresencial(nuevaClase);
            } catch (PersistenciaException e) {
                System.err.println("Error al agregar la clase al aula: " + e.getMessage());
            }
        }

        // Si tiene maestro, agregar la clase al maestro
        if (idMaestro != null) {
            try {
                maestroDAO.agregarClaseImpartida(nuevaClase);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al agregar la clase al maestro: " + e.getMessage());
            }
        }
    }

    private Integer obtenerSiguienteCodigo() {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Contador> coleccion = baseDatos.getCollection("counters", Contador.class);

        Contador actualizado = coleccion.findOneAndUpdate(
                Filters.eq("_id", "clase"),
                Updates.inc("codigoSecuencia", 1),
                new FindOneAndUpdateOptions()
                        .upsert(true)
                        .returnDocument(ReturnDocument.AFTER)
        );

        return actualizado.getCodigoSecuencia();
    }

    @Override
    public List<Clase> buscarNombreClases(String nombreClase) {
        Pattern patron = Pattern.compile(".*" + Pattern.quote(nombreClase) + ".*", Pattern.CASE_INSENSITIVE);

        return coleccion.find(Filters.regex("nombre", patron)).into(new ArrayList<>());
    }

    @Override
    public List<Clase> obtenerClases() {
        return coleccion.find().into(new ArrayList<>());
    }

    @Override
    public void editarClase(Clase editarClase) {
        //se actualizan los campos activa FechaFin, Hora Fin y capacidadAlumnos
        coleccion.updateOne(
                eq("_id", editarClase.getId()),
                Updates.combine(
                        Updates.set("activa", editarClase.isActiva()),
                        Updates.set("fechaFin", editarClase.getFechaFin()),
                        Updates.set("horaFin", editarClase.getHoraFin()),
                        Updates.set("capacidadAlumnos", editarClase.getCapacidadAlumnos())
                )
        );
    }

    @Override
    public void eliminarClase(Clase clase) {
        coleccion.deleteOne(eq("_id", clase.getId()));
    }

    @Override
    public List<Clase> obtenerClasesPorNombre(String nombreClase) {
        Pattern regex = Pattern.compile(nombreClase, Pattern.CASE_INSENSITIVE); // Coincidencia parcial y sin distinguir mayúsculas
        return coleccion.find(Filters.regex("nombre", regex)).into(new ArrayList<>());
    }

    @Override
    public Clase buscarClase(String codigo) {
        return coleccion.find(eq("_id", new ObjectId(codigo))).first();
    }

    @Override
    public Integer obtenerLimiteFaltas(Clase clase) {
        Clase claseEncontrada = coleccion.find(eq("_id", clase.getId())).first();
        return (claseEncontrada != null) ? claseEncontrada.getLIMITE_FALTAS() : null;
    }

    @Override
    public Clase buscarClaseCodigoInteger(Integer codigo) {
        return coleccion.find(eq("codigo", codigo)).first();
    }

    @Override
    public Integer obtenerCodigoMaxClase() {
        Clase claseConMaxCodigo = coleccion.find()
                .sort(new Document("codigo", -1)) // orden descendente por "codigo"
                .first();

        if (claseConMaxCodigo == null || claseConMaxCodigo.getCodigo() == null) {
            return 0; // Si no hay clases, empieza en 0
        }
        return claseConMaxCodigo.getCodigo();
    }

    @Override
    public List<Clase> obtenerClasesActivas() {
        return coleccion.find(eq("activa", true)).into(new ArrayList<>());
    }

    @Override
    public List<Clase> obtenerClasesInactivas() {
        return coleccion.find(eq("activa", false)).into(new ArrayList<>());
    }

    @Override
    public Clase buscarClaseObjectID(ObjectId idClase) {
        return coleccion.find(eq("_id", idClase)).first();
    }
}
