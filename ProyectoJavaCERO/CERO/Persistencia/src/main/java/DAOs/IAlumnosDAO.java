package DAOs;

import Entidades.Alumno;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IAlumnosDAO {
    
    public Alumno obtenerAlumno(String idAlumno);
    public Alumno registrarAlumnoNuevo(Alumno alumno);
    public List<Alumno> obtenerAlumnos();

}
