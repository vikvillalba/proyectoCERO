package implementaciones;

import Entidades.Clase;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import DAOs.IClasesDAO;
import java.time.LocalDate;
import java.time.Month;

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
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 30),
                "Cesar Gomez",
                new BigDecimal("250.00"),
                LocalDate.of(2025, Month.JANUARY, 12),
                LocalDate.of(2025, Month.JUNE, 15)
        );

        Clase clase2 = new Clase(
                2,
                "Contemporaneo principiante",
                Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
                LocalTime.of(11, 0),
                LocalTime.of(12, 30),
                "Juan Martinez",
                new BigDecimal("300.00"),
                LocalDate.of(2025, Month.JANUARY, 12),
                LocalDate.of(2025, Month.JUNE, 15)
        );

        clases.add(clase1);
        clases.add(clase2);
    }

    @Override
    public List<Clase> obtenerClasesPorNombre(String nombreClase) {
        List<Clase> clasesEncontradas = new ArrayList<>();
        String[] palabras = nombreClase.trim().toLowerCase().split("\\s+");

        for (Clase clase : this.clases) {
            String nombreExistente = clase.getNombre().toLowerCase();

            if (palabras.length == 1) {
                if (nombreExistente.contains(palabras[0])) {
                    clasesEncontradas.add(clase);
                }
            } else {
                boolean contieneTodas = true;
                for (String palabra : palabras) {
                    if (!nombreExistente.contains(palabra)) {
                        contieneTodas = false;
                        break;
                    }
                }
                if (contieneTodas) {
                    clasesEncontradas.add(clase);
                }
            }
        }

        return clasesEncontradas;
    }

    @Override
    public Clase buscarClase(Integer id) {
        for (Clase clase : clases) {
            if(clase.getCodigo().equals(id)){
                return clase;
            }
        }
        return null;
    }

    @Override
    public List<Clase> obtenerClases() {
        return this.clases;
    }

    @Override
    public Integer obtenerLimiteFaltas(Clase clase) {
        for (Clase clase1 : clases) {
            if (clase1.getCodigo().equals(clase.getCodigo())) {
                return clase1.getLIMITE_FALTAS();
            }
        }
        return 0;
    }

}
