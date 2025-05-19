/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import ConexionBD.ConexionMongoBD;
import DAOs.IClasesDAO;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Maestro;
import Excepciones.PersistenciaException;
import GestionarClasesPersistencia.AulaClaseDAO;
import GestionarClasesPersistencia.MaestroDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public class ClasesDAO implements IClasesDAO {

    private final MongoCollection<Clase> coleccion;
    private final AulaClaseDAO aulaClaseDAO;
    private final MaestroDAO maestroDAO;

    public ClasesDAO() {
        MongoDatabase db = ConexionMongoBD.getConexion();
        this.coleccion = db.getCollection("Clases", Clase.class);
        this.aulaClaseDAO = new AulaClaseDAO();
        this.maestroDAO = new MaestroDAO();
    }

    @Override
    public void registrarNuevaClase(Clase nuevaClase) {
        // Obtener el código máximo actual
        Integer codigoMaximo = obtenerCodigoMaxClase();
        nuevaClase.setCodigo(codigoMaximo + 1);

        ObjectId idAula = nuevaClase.getIdAula();
        ObjectId idMaestro = nuevaClase.getIdMaestro();

        // Validar existencia del aula si la modalidad es presencial
        if ("Presencial".equalsIgnoreCase(nuevaClase.getModalidad()) && idAula != null) {
            try {
                AulaClase aula = aulaClaseDAO.buscarClase(idAula);
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
                Maestro maestro = maestroDAO.buscarMaestro(idMaestro);
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

        // Agregar clase al arreglo de clasesPresenciales del aula (si aplica)
        if ("Presencial".equalsIgnoreCase(nuevaClase.getModalidad()) && idAula != null) {
            try {
                aulaClaseDAO.agregarClasePresencial(nuevaClase);
            } catch (PersistenciaException e) {
                System.err.println("Error al agregar la clase al aula: " + e.getMessage());
            }
        }

        // Agregar clase al arreglo de clasesImpartidas del maestro (si aplica)
        if (idMaestro != null) {
            try {
                maestroDAO.agregarClaseImpartida(nuevaClase);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al agregar la clase al maestro: " + e.getMessage());
            }
        }
    }


    @Override
    public List<Clase> buscarNombreClases(String nombreClase) {
        return coleccion.find(regex("nombre", ".*" + nombreClase + ".*", "i")).into(new ArrayList<>());
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
    public List<Clase> obtenerListaClasesMaestro(Maestro maestro) {
        return coleccion.find(eq("maestro.id", maestro.getId())).into(new ArrayList<>());
    }

    @Override
    public List<Clase> obtenerListaClasesAula(AulaClase aula) {
        return coleccion.find(eq("aula.id", aula.getId())).into(new ArrayList<>());
    }

    @Override
    public List<Clase> obtenerClasesPorNombre(String nombreClase) {
        return coleccion.find(eq("nombre", nombreClase)).into(new ArrayList<>());
    }

    @Override
    public Clase buscarClase(String codigo) {
        return coleccion.find(eq("_id", codigo)).first();
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
}
