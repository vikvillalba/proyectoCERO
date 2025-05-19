/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GestionarClasesPersistencia;

import ConexionBD.ConexionMongoBD;
import DAOs.IAsistenciasDAO;
import Entidades.Alumno;
import Entidades.Asistencia;
import Entidades.Clase;
import Entidades.TipoAsistencia;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import implementaciones.AsistenciasDAO;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Asistencia asistencia = new Asistencia();
        asistencia.setTipoAsistencia(TipoAsistencia.ASISTENCIA);
        asistencia.setFechaHora(LocalDateTime.now());
        asistencia.setAlumno(new ObjectId("682b940c46008c0e096940bd")); // ID del alumno como String
        asistencia.setClase(new ObjectId("682b9482d3fc123c05b01d37"));  // ID de la clase como String
        asistencia.setJustificante(null); // Sin justificante

        // Registrar asistencia
        IAsistenciasDAO dao = new AsistenciasDAO(); 
        Asistencia registrada = dao.registrarAsistencia(asistencia);

        System.out.println("Asistencia registrada con ID: " + registrada.getId());

    }
}


//        try {
//            Maestro maestroEncontrado = maestroDAO.buscarMaestro(maestro);
//            System.out.println("Maestro Encontrado");
//            System.out.println(maestroEncontrado.getNombreCompleto());
//
//            //Aula
//            AulaClase aula = new AulaClase();
//            ObjectId idAula = new ObjectId("682abdf0ae46729b99441534");
//            aula.setId(idAula);
//
//            AulaClase aulaEncontrada = aulaDAO.buscarClase(aula);
//            System.out.println("Aula Encontrada");
//            System.out.println(aulaEncontrada.getNombreAula());
//
//            //AGREGAR CLASE 
//            List<DayOfWeek> dias = new ArrayList<>();
//            dias.add(DayOfWeek.MONDAY);
//
//            LocalDate fechaInicio = LocalDate.of(2025, Month.MAY, 1);
//            LocalDate fechaFin = LocalDate.of(2025, Month.MAY, 30);
//
//            LocalTime horaInicio = LocalTime.of(8, 0);
//            LocalTime horaFin = LocalTime.of(10, 0);
//
//            Clase clase = new Clase(null,
//                    "CONTEMPORANEO PRUEBA",
//                    maestroEncontrado,
//                    "Presencial",
//                    dias,
//                    horaInicio,
//                    horaFin,
//                    fechaInicio,
//                    fechaFin,
//                    5,
//                    aulaEncontrada,
//                    BigDecimal.valueOf(200),
//                    true);
//
//            //agregarClase
//            System.out.println("Agregar clase");
//            claseDAO.registrarNuevaClase(clase);
//
//            System.out.println("Agregar ClasePresencial");
//            System.out.println("Clase agregada:" + clase.toString());
//            try {
//                aulaDAO.agregarClasePresencial(clase);
//            } catch (PersistenciaException ex) {
//                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//            }
////
////        System.out.println("gregar clase a maestro");
////        maestroDAO.agregarClaseImpartida(clase);
//        } catch (PersistenciaException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

