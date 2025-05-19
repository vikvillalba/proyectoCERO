/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GestionarClasesPersistencia;

import DAOs.IClasesDAO;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Maestro;
import Excepciones.PersistenciaException;
import implementaciones.AsistenciasDAO;
import implementaciones.ClasesDAO;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        ConexionBD.ConexionMongoBD.getConexion();
        IClasesDAO claseDAO = new ClasesDAO();
        IAulaClaseDAO aulaDAO = new AulaClaseDAO();
        IMaestroDAO maestroDAO = new MaestroDAO();

        List<Maestro> maestros = maestroDAO.obtenerMaestros();
        List<AulaClase> aulas = aulaDAO.obtenerAulas();

        //Obtener Maestro 
        Maestro maestro = new Maestro();
        ObjectId idMaestro = new ObjectId("682abe6f0de506d1b244152e");
        maestro.setId(idMaestro);
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
    
