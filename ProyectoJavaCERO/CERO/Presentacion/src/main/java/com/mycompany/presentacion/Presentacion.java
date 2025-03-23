package com.mycompany.presentacion;

import com.mycompany.inscribirclase.implementaciones.InscribirClase;
import com.mycompany.negocio.dtos.ClaseDTO;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Presentacion {

    public static void main(String[] args) {
        ControlNavegacion control = new ControlNavegacion(); // posible fábrica 

        // clase para probar. borrar después. 
        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY);
        ClaseDTO clase = new ClaseDTO(1, "Contemporáneo principiante", dias, LocalTime.of(18, 00), LocalTime.of(19, 15), "César Díaz", 500.0f);

        ControlNavegacion.mostrarPagoTarjeta(clase);
    }
}
