package com.mycompany.InterfazBO;

import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NuevaInscripcionDTO;

/**
 *
 * @author victoria
 */
public interface IInscripcionesBO {
    public InscripcionDTO registrarInscripcionPagoEfectivo(NuevaInscripcionDTO nuevaInscripcionDTO);
    
}
