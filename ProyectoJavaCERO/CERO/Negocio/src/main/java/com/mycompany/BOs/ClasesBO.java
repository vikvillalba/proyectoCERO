package com.mycompany.BOs;

import Entidades.Clase;
import InterfazDAOs.IClasesDAO;
import com.mycompany.InterfazBO.IClasesBO;
import com.mycompany.dtos.ClaseDTO;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victoria
 */
public class ClasesBO implements IClasesBO {

    private IClasesDAO clasesDAO;

    public ClasesBO(IClasesDAO clasesDAO) {
        this.clasesDAO = clasesDAO;
    }

    @Override
    public List<ClaseDTO> obtenerClasesNombre(String nombreClase) {
        List<Clase> clases = this.clasesDAO.obtenerClasesPorNombre(nombreClase);
        List<ClaseDTO> clasesObtenidas = new ArrayList<>();
        for (Clase clase : clases) {
            ClaseDTO claseDTO = new ClaseDTO(
                    clase.getCodigo(), 
                    clase.getNombre(), 
                    clase.getDias(), 
                    clase.getHoraInicio(), 
                    clase.getHoraFin(), 
                    clase.getMaestro(), 
                    clase.getPrecio());
            clasesObtenidas.add(claseDTO);
        }
        return clasesObtenidas;
    }

    @Override
    public List<ClaseDTO> obtenerClases() {
        List<Clase> clases = this.clasesDAO.obtenerClases();
        List<ClaseDTO> clasesObtenidas = new ArrayList<>();
        for (Clase clase : clases) {
            ClaseDTO claseDTO = new ClaseDTO(
                    clase.getCodigo(), 
                    clase.getNombre(), 
                    clase.getDias(), 
                    clase.getHoraInicio(), 
                    clase.getHoraFin(), 
                    clase.getMaestro(), 
                    clase.getPrecio());
            clasesObtenidas.add(claseDTO);
        }
        return clasesObtenidas;
    }

}
