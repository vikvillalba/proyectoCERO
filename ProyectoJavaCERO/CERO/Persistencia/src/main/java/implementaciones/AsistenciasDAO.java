package implementaciones;

import DAOs.IAsistenciasDAO;
import Entidades.Alumno;
import Entidades.Asistencia;
import Entidades.Clase;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz IAsistenciasDAO
 *
 * @author victoria
 */
public class AsistenciasDAO implements IAsistenciasDAO {

    private List<Asistencia> asistencias = new ArrayList<>();
    private Integer codigoAsistencia = 1;

    @Override
    public Asistencia registrarAsistencia(Asistencia asistencia) {
        asistencia.setId(codigoAsistencia);
        codigoAsistencia++;
        asistencias.add(asistencia);
        return asistencia;
    }

    @Override
    public Asistencia obtenerAsistenciaAlumnoClase(Alumno alumno, Clase clase) {
        for (Asistencia asistencia : asistencias) {
            if (asistencia.getAlumno() != null && asistencia.getClase() != null && asistencia.getFechaHora() != null) {
                if (asistencia.getAlumno().getCodigo().equals(alumno.getCodigo())
                        && asistencia.getClase().getCodigo().equals(clase.getCodigo())
                        && asistencia.getFechaHora().toLocalDate().equals(LocalDate.now())) {
                    return asistencia;
                }
            }
        }
        return null;
    }
}
