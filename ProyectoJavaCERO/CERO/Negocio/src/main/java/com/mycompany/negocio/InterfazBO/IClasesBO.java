package com.mycompany.negocio.InterfazBO;

import com.mycompany.dtos.ClaseDTO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IClasesBO {
    public List<ClaseDTO> obtenerClasesNombre(String nombreClase) throws NegocioException;
    public List<ClaseDTO> obtenerClases()throws NegocioException;
    public Integer obtenerLimiteFaltas(ClaseDTO clase);
    
}
