/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.negocio;

import com.mycompany.negocio.dtos.ClaseDTO;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Negocio {

    public static void main(String[] args) {

        // clase para probar. borrar después. 
        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY);
        ClaseDTO clase = new ClaseDTO(1, "Contemporánea principiante", dias, LocalTime.of(18, 00), LocalTime.of(19, 15), "César Díaz", new BigDecimal(500.00));
        // Inicialización de clases de danza
        ClaseDTO clase1 = new ClaseDTO(1, "Ballet Clásico", Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), LocalTime.of(17, 0), LocalTime.of(19, 0), "Maestra Ana Pérez", new BigDecimal("250.00"));
        ClaseDTO clase2 = new ClaseDTO(2, "Danza Contemporánea", Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY), LocalTime.of(18, 0), LocalTime.of(20, 0), "Maestro Carlos López", new BigDecimal("300.00"));
        ClaseDTO clase3 = new ClaseDTO(3, "Folklore Mexicano", Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY), LocalTime.of(16, 0), LocalTime.of(18, 0), "Maestra Sofía Ramírez", new BigDecimal("200.00"));

    }
}
