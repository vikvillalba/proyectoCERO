package com.mycompany.negocio.InterfazBO;

import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NuevaInscripcionDTO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IInscripcionesBO {
    public InscripcionDTO registrarInscripcionPagoEfectivo(NuevaInscripcionDTO nuevaInscripcionDTO);
    public InscripcionDTO registrarInscripcionPagoTarjeta(NuevaInscripcionDTO nuevaInscripcionDTO);
    public List<InscripcionDTO> obtenerInscripcionesAlumno(AlumnoDTO alumnoDTO) throws NegocioException;
    public List<InscripcionDTO> obtenerInscripcionesClase(ClaseDTO clase)throws NegocioException;
    
}
