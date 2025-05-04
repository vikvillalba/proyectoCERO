/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.Clase;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ClasesDAO implements IClasesDAO {

    @Override
    public List<Clase> obtenerClasesPorNombre(String nombreClase) {
        List<Clase> clasesEncontradas = new ArrayList();
        Clase clase1 = new Clase(
                1L,
                "Contemporaneo Avanzado",
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 30),
                "Cesar Gomez",
                new BigDecimal("2500.00")
        );

        Clase clase2 = new Clase(
                2L,
                "Contemporaneo principiante",
                Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
                LocalTime.of(11, 0),
                LocalTime.of(12, 30),
                "Juan Martinez",
                new BigDecimal("3000.00")
        );
        clasesEncontradas.add(clase1);
        clasesEncontradas.add(clase2);
        
        return clasesEncontradas;
    }

    @Override
    public Clase buscarClase(Long id) {
               Clase claseEntidad = new Clase(
                id,
                "Contemporaneo avanzado",
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 30),
                "Cesar Gomez",
                new BigDecimal("2500.00")
        );
               return claseEntidad;
    }
    
}
