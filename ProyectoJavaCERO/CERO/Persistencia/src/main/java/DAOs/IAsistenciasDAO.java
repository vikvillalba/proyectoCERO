package DAOs;

import Entidades.Alumno;
import Entidades.Asistencia;
import Entidades.Clase;

/**
 * interfaz que define las operaciones de persistencia para asistencias.
 * @author victoria
 */
public interface IAsistenciasDAO {
    public Asistencia registrarAsistencia(Asistencia asistencia);
    public Asistencia obtenerAsistenciaAlumnoClase(Alumno alumno, Clase clase);
}
