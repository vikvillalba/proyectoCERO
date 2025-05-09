package com.mycompany.negocio.InterfazBO;

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
