package ConexionBD;

import Entidades.Alumno;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Jck Murrrieta
 */
public class ConexionMongoBD {

    private static final String BASE_DATOS = "CeroBD";
    private static MongoClient cliente;
    private static MongoDatabase baseDatos;

    public static MongoDatabase getConexion() {
        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().register("Entidades").automatic(true).build()));

        MongoClientSettings configuraciones = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();

        // crea la conexion
        cliente = MongoClients.create(configuraciones);
        baseDatos = cliente.getDatabase(BASE_DATOS);
        return baseDatos;
    }

    public static void cerrarConexion() {
        if (cliente != null) {
            cliente.close();
        }
    }
}
