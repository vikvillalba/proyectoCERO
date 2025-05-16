package galeriaContenidos;

import Exceptions.GaleriaContenidosException;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.ContenidoBusquedaDTO;
import com.mycompany.dtos.ContenidoNuevoDTO;
import com.mycompany.dtos.ContenidoViejoDTO;
import java.util.List;

/**
 * Interfaz con la que interactua Presentacion con GaleriaContenido.
 * @author daniel
 */
public interface IGaleriaContenidos {
    
    public void leerContenido(String ruta) throws GaleriaContenidosException;
    public List<ClaseDTO> obtenerClases(String nombre) throws GaleriaContenidosException;
    public void seleccionarClase(ClaseDTO clase) throws GaleriaContenidosException;
    public boolean registrarContenido(ContenidoNuevoDTO contenido) throws GaleriaContenidosException;
    public boolean eliminarContenido(ContenidoViejoDTO contenido) throws GaleriaContenidosException;
    public boolean validarNombreContenidoRegistrar(String nombre) throws GaleriaContenidosException;
    public boolean validarAutorContenidoRegistrar(String autor) throws GaleriaContenidosException;
    public void seleccionarContenido(ContenidoViejoDTO contenido) throws GaleriaContenidosException;
    public List<ContenidoViejoDTO> obtenerListaContenidos(ContenidoBusquedaDTO contenido) throws GaleriaContenidosException;
    public ClaseDTO getClaseSeleccionada() throws GaleriaContenidosException;
    public ContenidoViejoDTO getContenidoViejoSeleccionado() throws GaleriaContenidosException;
    public void seleccionarContenidoViejo(ContenidoViejoDTO contenido) throws GaleriaContenidosException;
    public ContenidoNuevoDTO getContenidoNuevo();
    
    
}
