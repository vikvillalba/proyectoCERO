package com.mycompany.presentacion;

import com.mycompany.negocio.dtos.AlumnoDTO;
import com.mycompany.negocio.dtos.ClaseDTO;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public class Presentacion {

    public static void main(String[] args) {
        ControlNavegacion control = new ControlNavegacion(); // posible fábrica 
        ControlNavegacion.mostrarInscribirClase(control);
        

//        // clase para probar. borrar después. 
//        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY);
//        ClaseDTO clase = new ClaseDTO(1, "Contemporáneo principiante", dias, LocalTime.of(18, 00), LocalTime.of(19, 15), "César Díaz", new BigDecimal(500.00));
//         AlumnoDTO alumno = new AlumnoDTO(
//                12345, 
//                "Gómez", 
//                "Pérez", 
//                "Juan", 
//                "123-456-7890", 
//                LocalDate.of(1995, 5, 20), 
//                "juan.gomez@example.com"
//        );
//
//        ControlNavegacion.mostrarPagoEfectivo(clase, alumno);
    }
}
