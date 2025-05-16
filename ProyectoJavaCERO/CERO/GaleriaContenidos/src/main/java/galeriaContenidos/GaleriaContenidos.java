package galeriaContenidos;

import Exceptions.GaleriaContenidosException;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.ContenidoBusquedaDTO;
import com.mycompany.dtos.ContenidoNuevoDTO;
import com.mycompany.dtos.ContenidoViejoDTO;
import com.mycompany.negocio.Fabricas.FabricaObjetosNegocio;
import com.mycompany.negocio.InterfazBO.IClasesBO;
import com.mycompany.negocio.InterfazBO.IContenidoBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Clase control del subsistema Galeria de Contenidos.
 * @author daniel
 */
public class GaleriaContenidos implements IGaleriaContenidos {
    
    private ContenidoNuevoDTO contenidoNuevo = new ContenidoNuevoDTO();
    private ContenidoViejoDTO contenidoViejo;
    private ClaseDTO claseVieja;
    private IContenidoBO contenidoBO;
    private IClasesBO clasesBO;

    public GaleriaContenidos() {
        this.contenidoBO = FabricaObjetosNegocio.obtenerContenidoBO();
        this.clasesBO = FabricaObjetosNegocio.obtenerClasesBO();
    }
    
    @Override
    public void leerContenido(String ruta) throws GaleriaContenidosException {
        byte[] buffer = new byte[5 * 1024 * 1024];
        
        try (FileInputStream fileIn = new FileInputStream(ruta)) {
            
            byte[] datos = fileIn.readAllBytes();
            
            if ((fileIn.read(buffer) != -1)) {
                throw new GaleriaContenidosException("El tamaño máx. del archivo es de 5Mb.");
            }
            
            this.contenidoNuevo.setContenido(datos);
        } catch(IOException e) {
            throw new GaleriaContenidosException("No se pudo leer archivo.");
        }
    }
    
    @Override
    public List<ClaseDTO> obtenerClases(String nombre) throws GaleriaContenidosException {
        if (nombre.isBlank() || nombre == null) {
            throw new GaleriaContenidosException("El nombre no puede estar vacio.");
        }
        
        return null;
    }
    
    @Override
    public void seleccionarClase(ClaseDTO clase) throws GaleriaContenidosException {
        if (clase == null) {
            throw new GaleriaContenidosException("La Clase no puede estar vacia.");
        }
        
        this.contenidoNuevo.setClase(clase);
    }
    
    @Override
    public boolean registrarContenido(ContenidoNuevoDTO contenido) throws GaleriaContenidosException {
        if (contenido == null) {
            throw new GaleriaContenidosException("El contenido esta vacio.");
        }
        
        try {
            boolean exito = this.contenidoBO.registrarContenido(contenido);
            
            if (!exito) {
                throw new GaleriaContenidosException("No se pudo registrar el Contenido.");
            }
            
            return exito;
        } catch(NegocioException e) {
            throw new GaleriaContenidosException(e.getMessage());
        }
    }
    
    @Override
    public boolean eliminarContenido(ContenidoViejoDTO contenido) throws GaleriaContenidosException {
        if (contenido == null) {
            throw new GaleriaContenidosException("El contenido esta vacio.");
        }
                
        try {
            boolean exito = this.contenidoBO.eliminarContenido(contenido);
            
            if (!exito) {
                throw new GaleriaContenidosException("No se pudo eliminar el Contenido.");
            }
            
            return exito;
        } catch(NegocioException e) {
            throw new GaleriaContenidosException(e.getMessage());
        }
    }
    
    @Override
    public boolean validarNombreContenidoRegistrar(String nombre) throws GaleriaContenidosException {
        if (nombre.isBlank() || nombre == null) {
            throw new GaleriaContenidosException("El nombre no puede estar vacio.");
        }
        return true;
    }

    @Override
    public boolean validarAutorContenidoRegistrar(String autor) throws GaleriaContenidosException {
        if (autor.isBlank() || autor == null) {
            throw new GaleriaContenidosException("El autor no puede estar vacio.");
        }
        return true;
    }
    
    @Override
    public void seleccionarContenido(ContenidoViejoDTO contenido) throws GaleriaContenidosException {
        if (contenido == null) {
            throw new GaleriaContenidosException("El Contenido esta vacio.");
        }
        
        this.contenidoViejo = contenido;
    }
    
    @Override
    public List<ContenidoViejoDTO> obtenerListaContenidos(ContenidoBusquedaDTO contenido) throws GaleriaContenidosException {
        try {
            List<ContenidoViejoDTO> listaContenidos = this.contenidoBO.obtenerListaContenidos(contenido);
            
            if (listaContenidos.isEmpty() || listaContenidos == null) {
                throw new GaleriaContenidosException("No se encontraron contenidos.");
            }
            
            return listaContenidos;
        } catch(NegocioException e) {
            throw new GaleriaContenidosException(e.getMessage());
        }
    }
    
    @Override
    public ClaseDTO getClaseSeleccionada() throws GaleriaContenidosException {
        if (this.claseVieja == null) {
            throw new GaleriaContenidosException("No se ha seleccionado una Clase.");
        }
        
        return this.claseVieja;
    }
    
    @Override
    public ContenidoViejoDTO getContenidoViejoSeleccionado() throws GaleriaContenidosException {
        if (this.contenidoViejo == null) {
            throw new GaleriaContenidosException("No se ha seleccionado un Contenido.");
        }
        
        return this.contenidoViejo;
    }
    
    @Override
    public void seleccionarContenidoViejo(ContenidoViejoDTO contenido) throws GaleriaContenidosException {
        if (contenido == null) {
            throw new GaleriaContenidosException("El Contenido seleccionado no puede estar vacio.");
        }
        
        this.contenidoViejo = contenido;
    }
    
    @Override
    public ContenidoNuevoDTO getContenidoNuevo() {
        return this.contenidoNuevo;
    }
    
}
