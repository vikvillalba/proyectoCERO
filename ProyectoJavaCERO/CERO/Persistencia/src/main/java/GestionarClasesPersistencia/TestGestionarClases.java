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
import implementaciones.ClasesDAO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */
public class TestGestionarClases {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Asistencia asistencia = new Asistencia();
//        asistencia.setTipoAsistencia(TipoAsistencia.ASISTENCIA);
//        asistencia.setFechaHora(LocalDateTime.now());
//        asistencia.setAlumno(new ObjectId("682b940c46008c0e096940bd")); // ID del alumno como String
//        asistencia.setClase(new ObjectId("682b9482d3fc123c05b01d37"));  // ID de la clase como String
//        asistencia.setJustificante(null); // Sin justificante
        ConexionBD.ConexionMongoBD.getConexion();
//        IClasesDAO claseDAO = new ClasesDAO();
//        IAulasClaseDAO aulaDAO = new AulasClaseDAO();
//        IMaestrosDAO maestroDAO = new MaestrosDAO();
////
////        // Registrar asistencia
////        IAsistenciasDAO dao = new AsistenciasDAO();
////        Asistencia registrada = dao.registrarAsistencia(asistencia);
////
////        System.out.println("Asistencia registrada con ID: " + registrada.getId());
//
//        //Obtener Maestro 
//        ObjectId idMaestro = new ObjectId("682ba310436dbf6234441534");
//        String sIDMaestro = "682ba310436dbf6234441534";
//        List<Clase> clasesImpartidas = maestroDAO.obtenerClasesImpartidas(sIDMaestro);
//         
//        Maestro maestroEncontrado = maestroDAO.buscarMaestro(sIDMaestro);
//        System.out.println("Clases Impartidas del maestro:" + maestroEncontrado.getNombreCompleto());
//        System.out.println("Clases impartidas (IDs): " + maestroEncontrado.getClasesImpartidas());
//        for (Clase clasesImpartida : clasesImpartidas) {
//
//            System.out.println(clasesImpartida.toString());
//
//        }
//                    //Aula
//            AulaClase aula = new AulaClase();
//            String idAula = "682ba34d436dbf6234441536";
//
//            AulaClase aulaEncontrada = aulaDAO.buscarClase(idAula);
//            System.out.println("Aula Encontrada");
//            System.out.println(aulaEncontrada.getNombreAula());
//            List<Clase> clasesPresenciales = aulaDAO.obtenerAulaClases(idAula);
//            for (Clase clasePresencial : clasesPresenciales) {
//                System.out.println(clasePresencial.toString());
//            
//        }
    }

}
