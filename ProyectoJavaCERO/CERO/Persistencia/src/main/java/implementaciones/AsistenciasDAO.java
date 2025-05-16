package implementaciones;

import DAOs.IAsistenciasDAO;
import Entidades.Alumno;
import Entidades.Asistencia;
import Entidades.Clase;
import Entidades.TipoAsistencia;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementación de la interfaz IAsistenciasDAO
 *
 * @author victoria
 */
public class AsistenciasDAO implements IAsistenciasDAO {

    private List<Asistencia> asistencias = new ArrayList<>();
    private Integer codigoAsistencia = 1;

    public AsistenciasDAO() {
        Clase claseMock = new Clase(
                1,
                "Contemporaneo Avanzado",
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 30),
                "Cesar Gomez",
                new BigDecimal("250.00"),
                LocalDate.of(2025, Month.JANUARY, 12),
                LocalDate.of(2025, Month.JUNE, 15)
        );

        Alumno alumnoMock = new Alumno(
                1,
                "Torres",
                "Murrieta",
                "Jack Tadeo",
                "5551234567",
                LocalDate.of(2002, 8, 15),
                "jackmurrieta@gmail.com"
        );
        // asistencia de lunes 12
        Asistencia asistenciaAnterior1 = new Asistencia(
                1,
                TipoAsistencia.FALTA,
                LocalDateTime.of(2025, Month.MAY, 12, 0, 0),
                alumnoMock,
                claseMock
        );
        Asistencia asistenciaAnterior2 = new Asistencia(
                2,
                TipoAsistencia.JUSTIFICADO,
                LocalDateTime.of(2025, Month.MAY, 14, 0, 0),
                alumnoMock,
                claseMock
        );
        Asistencia asistenciaAnterior3 = new Asistencia(
                3,
                TipoAsistencia.JUSTIFICADO,
                LocalDateTime.of(2025, Month.MAY, 7, 0, 0),
                alumnoMock,
                claseMock
        );
        Asistencia asistenciaAnterior4 = new Asistencia(
                4,
                TipoAsistencia.JUSTIFICADO,
                LocalDateTime.of(2025, Month.APRIL, 16, 0, 0),
                alumnoMock,
                claseMock
        );
        this.asistencias.add(asistenciaAnterior1);
        this.asistencias.add(asistenciaAnterior2);
        this.asistencias.add(asistenciaAnterior3);
        this.asistencias.add(asistenciaAnterior4);
        codigoAsistencia = 5;
    }

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

    @Override
    public List<Asistencia> obtenerAsistenciasAlumnos(Clase clase, LocalDate fechaClase) {
        List<Asistencia> asistenciasClase = new ArrayList<>();

        for (Asistencia asistencia : asistencias) {
            if (asistencia.getClase() != null && asistencia.getFechaHora() != null) {

                if (asistencia.getClase().getCodigo().equals(clase.getCodigo())
                        && asistencia.getFechaHora().toLocalDate().equals(fechaClase)) {
                    asistenciasClase.add(asistencia);
                }
            }
        }
        return asistenciasClase;

    }

    @Override
    public Asistencia justificarFalta(Asistencia faltaJustificada) {
        for (int i = 0; i < asistencias.size(); i++) {
            Asistencia asistencia = asistencias.get(i);

            if (asistencia.getId().equals(faltaJustificada.getId())) {
                asistencias.set(i, faltaJustificada);
                return faltaJustificada;
            }
        }
        return null;
    }

    @Override
    public List<Asistencia> obtenerFaltasJustificadasAlumnoClase(Alumno alumno, Clase clase) {
        List<Asistencia> faltasJustificadas = new ArrayList<>();
        for (Asistencia asistencia : asistencias) {
            if (asistencia.getAlumno().getCodigo().equals(alumno.getCodigo())
                    && asistencia.getClase().getCodigo().equals(clase.getCodigo())
                    && asistencia.getTipoAsistencia().equals(TipoAsistencia.JUSTIFICADO)) {
                faltasJustificadas.add(asistencia);
            }

        }
        return faltasJustificadas;
    }

    @Override
    public List<Asistencia> actualizarAsistencias(List<Asistencia> nuevasAsistencias) {
        for (Asistencia nueva : nuevasAsistencias) {
            boolean encontrada = false;

            for (int i = 0; i < asistencias.size(); i++) {
                if (asistencias.get(i).getId().equals(nueva.getId())) {
                    asistencias.set(i, nueva);
                    encontrada = true;
                    break;
                }
            }

            if (!encontrada) {
                asistencias.add(nueva);
            }
        }
        return nuevasAsistencias;
    }

}
