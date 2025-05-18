package implementaciones;

import ConexionBD.ConexionMongoBD;
import Entidades.Pago;
import DAOs.IPagosDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Usuario
 */
public class PagosDAO implements IPagosDAO {

    private final String COLECCION = "Pagos";

    @Override
    public Pago registrarPago(Pago pago) {
        MongoDatabase baseDatos = ConexionMongoBD.getConexion();
        MongoCollection<Pago> coleccion = baseDatos.getCollection(COLECCION, Pago.class);
        coleccion.insertOne(pago);
        return pago;

    }

}
