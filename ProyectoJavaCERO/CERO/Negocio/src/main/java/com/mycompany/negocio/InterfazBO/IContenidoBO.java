package com.mycompany.negocio.InterfazBO;

import com.mycompany.dtos.ContenidoBusquedaDTO;
import com.mycompany.dtos.ContenidoNuevoDTO;
import com.mycompany.dtos.ContenidoViejoDTO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface IContenidoBO {
    
    public boolean registrarContenido(ContenidoNuevoDTO contenido) throws NegocioException;
    public boolean eliminarContenido(ContenidoViejoDTO contenido) throws NegocioException;
    public List<ContenidoViejoDTO> obtenerListaContenidos(ContenidoBusquedaDTO contenido) throws NegocioException;
    public byte[] obtenerBytesContenido(ContenidoViejoDTO contenido) throws NegocioException;
}
