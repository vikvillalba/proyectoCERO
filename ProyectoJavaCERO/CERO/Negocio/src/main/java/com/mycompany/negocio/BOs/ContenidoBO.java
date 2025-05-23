package com.mycompany.negocio.BOs;

import Entidades.Clase;
import Entidades.Contenido;
import Excepciones.PersistenciaException;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.ContenidoBusquedaDTO;
import com.mycompany.dtos.ContenidoNuevoDTO;
import com.mycompany.dtos.ContenidoViejoDTO;
import com.mycompany.negocio.InterfazBO.IContenidoBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.List;
import java.util.stream.Collectors;
import DAOs.IContenidoDAO;
import org.bson.types.ObjectId;

/**
 *
 * @author daniel
 */
public class ContenidoBO implements IContenidoBO {
    
    private IContenidoDAO contenidoDAO;
    
    public ContenidoBO(IContenidoDAO contenidoDAO) {
        this.contenidoDAO = contenidoDAO;
    }

    @Override
    public boolean registrarContenido(ContenidoNuevoDTO contenido) throws NegocioException {
        ClaseDTO clase = contenido.getClase();
        Clase claseEn = new Clase();
        claseEn.setCodigo(contenido.getClase().getCodigo());
        
        Contenido c = new Contenido(
                    contenido.getNombre(),
                    contenido.getAutor(),
                    contenido.getFechaHora(),
                    contenido.getContenido(),
                    contenido.getClase().getCodigo()
        );
        
        try {
            Contenido contenidoGuardado = this.contenidoDAO.registrarContenido(c);
            if (contenidoGuardado.getId() == null) {
                throw new NegocioException("No se pudo guardar el Contenido.");
            }
            
            return true;
        } catch(PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
        
    }

    @Override
    public boolean eliminarContenido(ContenidoViejoDTO contenido) throws NegocioException {
        ObjectId id = (ObjectId) contenido.getId();
        Contenido c = new Contenido();
        c.setId(id);
        
        try {
            boolean exito = this.contenidoDAO.eliminarContenido(c);
            
            if (!exito) {
                throw new NegocioException("No se pudo eliminar el contenido.");
            }
            
            return exito;
        } catch(PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<ContenidoViejoDTO> obtenerListaContenidos(ContenidoBusquedaDTO contenido) throws NegocioException {
        ClaseDTO clase = contenido.getClase();
        Clase claseEntity = new Clase();
        claseEntity.setCodigo(clase.getCodigo());
        
        
        try {
            List<Contenido> contenidos = this.contenidoDAO.obtenerListaContenidos(
                    contenido.getNombre(),
                    contenido.getAutor(),
                    contenido.getFechaHora(),
                    claseEntity
            );
            
            return contenidos.stream()
                             .map(c -> new ContenidoViejoDTO(
                                     c.getId(),
                                     c.getNombre(),
                                     c.getAutor(),
                                     c.getFechaHora(),
                                     new ClaseDTO(c.getCodigoClase())
                                     ))
                              .collect(Collectors.toList());
        } catch(PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public byte[] obtenerBytesContenido(ContenidoViejoDTO contenido) throws NegocioException {
        Clase clase = new Clase();
        clase.setCodigo(contenido.getClase().getCodigo());
        
        Contenido c = new Contenido(
                 (ObjectId) contenido.getId(),
                 contenido.getNombre(),
                 contenido.getAutor(),
                 contenido.getFechaHora(),
                 clase.getCodigo()
        );
        
        try {
            byte[] datos = this.contenidoDAO.obtenerBytesContenido(c);
            
            return datos;
        } catch(PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
}
