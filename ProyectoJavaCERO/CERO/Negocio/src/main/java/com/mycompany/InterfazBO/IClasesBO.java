package com.mycompany.InterfazBO;

import com.mycompany.dtos.ClaseDTO;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IClasesBO {
    public List<ClaseDTO> obtenerClasesNombre(String nombreClase);
    public List<ClaseDTO> obtenerClases();
    
}
