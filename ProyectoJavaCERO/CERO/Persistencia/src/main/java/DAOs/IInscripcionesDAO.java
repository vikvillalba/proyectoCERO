
package DAOs;

import Entidades.Alumno;
import Entidades.Inscripcion;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IInscripcionesDAO {
    public List<Inscripcion> obtenerInscripcionesClase(Integer idClase);
    
    public Inscripcion registrarInscripcion(Inscripcion inscripcion);
    public List<Inscripcion> obtenerInscripcionesAlumno(Alumno alumno);
    public List<Inscripcion>obtenerInscripcionesAlumnoDiaActual(Alumno alumno);
    
}
