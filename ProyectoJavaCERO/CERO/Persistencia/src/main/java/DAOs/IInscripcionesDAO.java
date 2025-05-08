
package DAOs;

import Entidades.Inscripcion;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IInscripcionesDAO {
    public List<Inscripcion> obtenerInscripcionesClase(Integer idClase);
    
    public Inscripcion registrarInscripcion(Inscripcion inscripcion);
    
}
