
package DAOs;

import Entidades.Alumno;
import Entidades.Inscripcion;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public interface IInscripcionesDAO {
    public List<Inscripcion> obtenerInscripcionesClase(String idClase);
    
    public Inscripcion registrarInscripcion(Inscripcion inscripcion);
    public List<Inscripcion> obtenerInscripcionesAlumno(Alumno alumno);
    public List<Inscripcion>obtenerInscripcionesAlumnoDiaActual(Alumno alumno);
    public List<Alumno> obtenerAlumnosInscritosClase(String idClase);
    
    public void cancelarInscripcion(String idInscripcion);

    // obtener total de inscritos 
    public int contarInscripcionesPorClase(ObjectId idClase);
    
}
