package DAOs;

import Entidades.Clase;
import Entidades.Contenido;
import Excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface IContenidoDAO {
    
    public Contenido registrarContenido(Contenido contenido) throws PersistenciaException;
    public boolean eliminarContenido(Contenido contenido) throws PersistenciaException;
    public List<Contenido> obtenerListaContenidos(String nombre, String autor, LocalDateTime fechaHora, Clase clase) throws PersistenciaException;
    public byte[] obtenerBytesContenido(Contenido contenido) throws PersistenciaException;
}
