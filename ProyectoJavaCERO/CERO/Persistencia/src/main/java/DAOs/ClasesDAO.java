package DAOs;

import Entidades.Clase;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import InterfazDAOs.IClasesDAO;

/**
 *
 * @author Usuario
 */
public class ClasesDAO implements IClasesDAO {

    private List<Clase> clases;

    public ClasesDAO() {
        this.clases = new ArrayList<>();
        Clase clase1 = new Clase(
                1,
                "Contemporaneo Avanzado",
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 30),
                "Cesar Gomez",
                new BigDecimal("250.00")
        );

        Clase clase2 = new Clase(
                2,
                "Contemporaneo principiante",
                Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
                LocalTime.of(11, 0),
                LocalTime.of(12, 30),
                "Juan Martinez",
                new BigDecimal("300.00")
        );

        clases.add(clase1);
        clases.add(clase2);
    }

    @Override
    public List<Clase> obtenerClasesPorNombre(String nombreClase) {
        List<Clase> clasesEncontradas = new ArrayList<>();
        String[] palabras = nombreClase.trim().split("\\s+");

        for (Clase clase : this.clases) {
            String nombreExistente = clase.getNombre().toLowerCase();
            for (String palabra : palabras) {
                if (nombreExistente.contains(palabra.toLowerCase())) {
                    clasesEncontradas.add(clase);
                    break;
                }
            }
        }
        return clasesEncontradas;
    }

    @Override
    public Clase buscarClase(Integer id) {
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

    @Override
    public List<Clase> obtenerClases() {
        return this.clases;
    }

}
