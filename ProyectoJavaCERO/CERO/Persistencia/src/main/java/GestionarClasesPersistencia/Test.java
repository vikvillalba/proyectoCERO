package GestionarClasesPersistencia;

import DAOs.IAlumnosDAO;
import DAOs.IClasesDAO;
import Entidades.Alumno;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Inscripcion;
import Entidades.Maestro;
import Entidades.MetodoPago;
import Entidades.MetodoPagoEfectivo;
import Entidades.Pago;
import implementaciones.AlumnosDAO;
import implementaciones.ClasesDAO;
import implementaciones.InscripcionesDAO;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import DAOs.IInscripcionesDAO;

/**
 *
 * @author Usuario
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConexionBD.ConexionMongoBD.getConexion();
        IClasesDAO claseDAO = new ClasesDAO();
        IAulasClaseDAO aulaDAO = new AulasClaseDAO();
        IMaestrosDAO maestroDAO = new MaestrosDAO();
        IAlumnosDAO alumnosDAO = new AlumnosDAO();
//
        IInscripcionesDAO inscripcionesDAO = new InscripcionesDAO();
//
//        Maestro maestro1 = new Maestro(
//                "Díaz",
//                "Pérez",
//                "César Obed",
//                "6445678976",
//                LocalDate.of(1999, 07, 12),
//                "example1@gmail.com",
//                "cesar123"
//        );
//
//        Maestro maestroRegistrado = maestroDAO.agregarMaestro(maestro1);
//
//        AulaClase aulaClase = new AulaClase("Salón principal");
//        AulaClase aulaRegistrada = aulaDAO.agregarAula(aulaClase);
//
//        List<DayOfWeek> dias = new ArrayList<>();
//        dias.add(DayOfWeek.MONDAY);
//        dias.add(DayOfWeek.FRIDAY);
//
//        LocalDate fechaInicio = LocalDate.of(2025, Month.MAY, 1);
//        LocalDate fechaFin = LocalDate.of(2025, Month.MAY, 30);
//
//        LocalTime horaInicio = LocalTime.of(8, 0);
//        LocalTime horaFin = LocalTime.of(10, 0);
////
//        Clase clase1 = new Clase(
//                null,
//                "Contemporáneo Principiante",
//                maestroRegistrado.getId(),
//                aulaRegistrada.getId(),
//                "Presencial",
//                dias,
//                horaInicio,
//                horaFin,
//                fechaInicio,
//                fechaFin,
//                20,
//                new BigDecimal(500),
//                true
//        );
//
////        claseDAO.registrarNuevaClase(clase1);
//        Alumno alumno = alumnosDAO.obtenerAlumno("682bb2a25a8c9265a446e937");
//        Clase clase = claseDAO.buscarClase("682bb177f1a13c3c4fa23f7c");
//        MetodoPago metodoPago = new MetodoPagoEfectivo(new BigDecimal(2000), new BigDecimal(500));
//        Pago pago = new Pago(clase.getPrecio(), LocalDateTime.now(), true, metodoPago);
//        Inscripcion inscripcion = new Inscripcion(clase.obtenerIdString(), alumno.getIdString(), LocalDateTime.now(), pago);
//        
//        Inscripcion inscripcionRegistrada = inscripcionesDAO.registrarInscripcion(inscripcion);

    }
}
