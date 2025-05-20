package DAOs;

import Entidades.Alumno;
import Entidades.Asistencia;
import Entidades.Clase;
import Entidades.ReporteAsistencia;
import java.time.LocalDate;
import java.util.List;

/**
 * interfaz que define las operaciones de persistencia para asistencias.
 * @author victoria
 */
public interface IAsistenciasDAO {
    public Asistencia registrarAsistencia(Asistencia asistencia);
    public Asistencia obtenerAsistenciaAlumnoClase(Alumno alumno, Clase clase);
    public List<Asistencia>obtenerAsistenciasAlumnos(Clase clase, LocalDate fechaClase);
    public Asistencia justificarFalta(Asistencia faltaJustificada);
    public List<Asistencia> obtenerFaltasJustificadasAlumnoClase(Alumno alumno, Clase clase);
    public List<Asistencia> actualizarAsistencias(List<Asistencia> asistencia, String idClase);
    public List<ReporteAsistencia> obtenerReporteAsistencias(String idAlumno, String idClase, LocalDate fechaInicio, LocalDate fechaFin);
}
