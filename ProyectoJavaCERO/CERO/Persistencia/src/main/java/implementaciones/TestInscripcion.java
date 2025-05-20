/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package implementaciones;

import Entidades.Alumno;
import Entidades.Clase;
import Entidades.Inscripcion;
import Entidades.MetodoPago;
import Entidades.MetodoPagoEfectivo;
import Entidades.Pago;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TestInscripcion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // DAOs
        ConexionBD.ConexionMongoBD.getConexion();
        
        AlumnosDAO alumnosDAO = new AlumnosDAO();
        ClasesDAO clasesDAO = new ClasesDAO();
        InscripcionesDAO inscripcionesDAO = new InscripcionesDAO();
        PagosDAO pagoDAO = new PagosDAO();

        // === 1. Crear un nuevo alumno ===
        Alumno alumno = new Alumno();
        alumno.setNombre("Ana");
        alumno.setApellidoPaterno("García");
        alumno.setApellidoMaterno("Torres");
        alumno.setCorreoElectronico("ana.garcia@example.com");
        alumno.setTelefono("5551234567");
        alumno.setFechaNacimiento(LocalDate.of(2002, 3, 25));

//        alumno = alumnosDAO.registrarAlumnoNuevo(alumno);
        System.out.println("Alumno creado con ID: " + alumno.getId());

        // === 2. Crear una nueva clase ===
        Clase clase = new Clase();
        clase.setNombre("Programación Avanzada");
        clase.setModalidad("Presencial");
        clase.setFechaInicio(LocalDate.of(2025, 6, 1));
        clase.setFechaFin(LocalDate.of(2025, 8, 31));

//        clasesDAO.registrarNuevaClase(clase);
        System.out.println("Clase creada con ID: " + clase.getId());

        // === 3. Crear la inscripción ===
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setAlumno(alumno.getId());
        inscripcion.setClase(clase.getId());
        MetodoPago metodoPago = new MetodoPagoEfectivo(new BigDecimal(2000), new BigDecimal(500));
        Pago pago = new Pago(clase.getPrecio(), LocalDateTime.now(), true, metodoPago);
//        inscripcion.setFechaInscripcion(LocalDateTime.now());
        
        Pago pagoCreado = pagoDAO.registrarPago(pago);
        
        inscripcion.setPago(pagoCreado);
        


//        inscripcion = inscripcionesDAO.registrarInscripcion(inscripcion);
        System.out.println("Inscripción creada con ID: " + inscripcion.getId());

        // === 4. Visualizar inscripción completa ===
        System.out.println("=== Inscripción Guardada ===");
        System.out.println("ID: " + inscripcion.getId());
        System.out.println("Alumno: " + inscripcion.getAlumno());
        System.out.println("Clase: " + inscripcion.getClase());
        System.out.println("Fecha inscripción: " + inscripcion.getFechaInscripcion());
        
        System.out.println("------------------");
        Alumno alumnoEncontrado = alumnosDAO.obtenerAlumno("682c45dd9c0bd93c4671786b");
        List<Inscripcion> inscripcionesAlumno = inscripcionesDAO.obtenerInscripcionesAlumno(alumnoEncontrado);
        for (Inscripcion inscripcion1 : inscripcionesAlumno) {
            System.out.println(inscripcion1.toString());
            
        }
    }
}
