/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import DTOs.GestionarClases.AulaClaseDTO;
import DTOs.GestionarClases.MaestroDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import com.mycompany.negocio.BOs.ClasesBO;
import com.mycompany.negocio.Fabricas.FabricaObjetosNegocio;
import com.mycompany.negocio.InterfazBO.IClasesBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class TestNegocio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        IClasesBO clasesbo = FabricaObjetosNegocio.obtenerClasesBO();
        
        
        MaestroDTO maestroDTO = new MaestroDTO();
        maestroDTO.setId("682b0ded0de506d1b2441530");
        AulaClaseDTO aulaDTO = new AulaClaseDTO();
        aulaDTO.setIdAula("682b0de7ae46729b99441536");

        //AGREGAR CLASE 
        List<DayOfWeek> dias = new ArrayList<>();
        dias.add(DayOfWeek.FRIDAY);

        LocalDate fechaInicio = LocalDate.of(2025, Month.MAY, 1);
        LocalDate fechaFin = LocalDate.of(2025, Month.MAY, 30);

        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(10, 0);

        NuevaClaseDTO nuevaClase = new NuevaClaseDTO(null,
                "HIP HOP",
                maestroDTO,
                "Presencial",
                aulaDTO,
                dias,
                horaInicio,
                horaFin,
                fechaInicio,
                fechaFin,
                5,
                300,
                true);
        
        try {
            //Agregar clase
            clasesbo.registrarNuevaClase(nuevaClase);
            System.out.println("Clase registrada exitosamente");
        } catch (NegocioException ex) {
            Logger.getLogger(TestNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
