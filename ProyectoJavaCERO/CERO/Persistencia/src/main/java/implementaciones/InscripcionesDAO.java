package implementaciones;

import Entidades.Alumno;
import Entidades.Clase;
import Entidades.Inscripcion;
import Entidades.MetodoPagoTarjeta;
import Entidades.Pago;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import DAOs.IInscripcionesDAO;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class InscripcionesDAO implements IInscripcionesDAO {

    private List<Inscripcion> inscripciones;
    private Integer codigoInscripcion = 1;

    public InscripcionesDAO() {
        this.inscripciones = new ArrayList<>();
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
        MetodoPagoTarjeta metodoPagoTarjetaMock = new MetodoPagoTarjeta(
                2,
                "CONFIRM123456",
                LocalDateTime.now()
        );
        Pago pagoMock = new Pago(1, new BigDecimal("500.00"), LocalDateTime.now(), true, metodoPagoTarjetaMock);

        // Crear una inscripción mock
        Inscripcion inscripcionMock = new Inscripcion(
                1,
                claseMock,
                alumnoMock,
                LocalDateTime.now(),
                pagoMock
        );

        inscripciones.add(inscripcionMock);
    }

    @Override
    public List<Inscripcion> obtenerInscripcionesClase(Integer idClase) {
        List<Inscripcion> inscripcionesClase = new ArrayList<>();
        for (Inscripcion inscripcion : inscripciones) {
            if(inscripcion.getClase().getCodigo().equals(idClase)){
                inscripcionesClase.add(inscripcion);
            }
        }
        return inscripcionesClase;
    }

    @Override
    public Inscripcion registrarInscripcion(Inscripcion inscripcion) {
        inscripcion.setId(codigoInscripcion);
        codigoInscripcion++;
        this.inscripciones.add(inscripcion);
        return inscripcion;

    }

    @Override
    public List<Inscripcion> obtenerInscripcionesAlumno(Alumno alumno) {
        List<Inscripcion> inscripcionesAlumno = new ArrayList<>();
        for (Inscripcion inscripcion : this.inscripciones) {
            if (inscripcion.getAlumno().getCodigo().equals(alumno.getCodigo())) {
                inscripcionesAlumno.add(inscripcion);
            }
        }

        return inscripcionesAlumno;
    }

    @Override
    public List<Inscripcion> obtenerInscripcionesAlumnoDiaActual(Alumno alumno) {
        DayOfWeek diaActual = LocalDate.now().getDayOfWeek();
        List<Inscripcion> inscripcionesAlumno = new ArrayList<>();
        for (Inscripcion inscripcion : this.inscripciones) {
            if (inscripcion.getAlumno().getCodigo().equals(alumno.getCodigo()) && inscripcion.getClase().getDias().contains(diaActual)) {
                inscripcionesAlumno.add(inscripcion);
            }
        }

        return inscripcionesAlumno;
    }

}
