package implementaciones;

import ConexionBD.ConexionMongoBD;
import DAOs.IContenidoDAO;
import Entidades.Clase;
import Entidades.Contenido;
import Excepciones.PersistenciaException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.include;
import com.mongodb.client.result.DeleteResult;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;

/**
 *
 * @author daniel
 */
public class ContenidoMongoDAO implements IContenidoDAO {

    private final MongoCollection<Contenido> coleccion;
    
    public ContenidoMongoDAO() {
        MongoDatabase db = ConexionMongoBD.getConexion();
        this.coleccion = db.getCollection("contenidos", Contenido.class);
    }

    @Override
    public Contenido registrarContenido(Contenido contenido) throws PersistenciaException {
        try {
            coleccion.insertOne(contenido);
            
            return contenido;
        } catch(Exception e) {
            throw new PersistenciaException("No se pudo registrar el Contenido.");
        }
    }

    @Override
    public boolean eliminarContenido(Contenido contenido) throws PersistenciaException {
        Bson filtro = Filters.eq("_id", contenido.getId());
        
        try {
            DeleteResult exito = coleccion.deleteMany(filtro);
            if (exito.getDeletedCount() == 0) {
                return false;
            }
            
            return true;
        } catch(Exception e) {
            throw new PersistenciaException("No se pudo eliminar el Contenido.");
        }
    }

    @Override
    public List<Contenido> obtenerListaContenidos(String nombre, String autor, LocalDateTime fechaHora, Clase clase) throws PersistenciaException {
        List<Bson> filtros = new ArrayList<>();
        filtros.add(Filters.eq("codigoClase", clase.getCodigo()));
        
        if (nombre != null) {
            filtros.add(Filters.regex("nombre", nombre));
        }
        if (autor != null) {
            filtros.add(Filters.regex("autor", autor));
        }
        if (fechaHora != null) {
            Filters.gte("fechaHora", fechaHora);
        }
        
        Bson filtroFinal = filtros.isEmpty() ? new BsonDocument() : Filters.and(filtros);
        
        try {
            return coleccion.find(filtroFinal).into(new ArrayList<>());
        } catch(Exception e) {
            throw new PersistenciaException("No se pudo consultar los Contenidos.");
        }
    }

    @Override
    public byte[] obtenerBytesContenido(Contenido contenido) throws PersistenciaException {
        try {
            Contenido resultado = coleccion.find(eq("_id", contenido.getId()))
                              .projection(include("contenido"))
                              .projection(excludeId())    
                              .first();
            
            return resultado.getContenido();
        } catch(Exception e) {
            throw new PersistenciaException("No se pudo consultar los datos del Contenido.");
        }
    }
    
}
