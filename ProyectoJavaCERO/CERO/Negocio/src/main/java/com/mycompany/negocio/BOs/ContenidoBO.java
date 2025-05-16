package com.mycompany.negocio.BOs;

import DAOs.IContenidoDAO;
import Entidades.Clase;
import Entidades.Contenido;
import Excepciones.PersistenciaException;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.ContenidoBusquedaDTO;
import com.mycompany.dtos.ContenidoNuevoDTO;
import com.mycompany.dtos.ContenidoViejoDTO;
import com.mycompany.negocio.InterfazBO.IContenidoBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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
        
        Contenido c = new Contenido(
                    contenido.getNombre(),
                    contenido.getAutor(),
                    contenido.getFechaHora(),
                    contenido.getContenido(),
                    new Clase(clase.getNombre(),
                            clase.getDias(),
                            clase.getHoraInicio(),
                            clase.getHoraFin(), 
                            clase.getMaestro(),
                            clase.getPrecio(),
                            clase.getFechaInicio(),
                            clase.getFechaFin())
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
        Integer id = (Integer) contenido.getId();
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
        
        try {
            List<Contenido> contenidos = this.contenidoDAO.obtenerListaContenidos(
                    contenido.getNombre(),
                    contenido.getAutor(),
                    contenido.getFechaHora(),
                    new Clase(
                            clase.getNombre(),
                            clase.getDias(),
                            clase.getHoraInicio(),
                            clase.getHoraFin(), 
                            clase.getMaestro(),
                            clase.getPrecio(),
                            clase.getFechaInicio(),
                            clase.getFechaFin())
            );
            
            return contenidos.stream()
                             .map(c -> new ContenidoViejoDTO(
                                     c.getId(),
                                     c.getNombre(),
                                     c.getAutor(),
                                     c.getFechaHora(),
                                     new ClaseDTO(
                                             c.getClase().getCodigo(),
                                             c.getClase().getNombre(),
                                             c.getClase().getDias(),
                                             c.getClase().getHoraInicio(),
                                             c.getClase().getHoraFin(), 
                                             c.getClase().getMaestro(),
                                             c.getClase().getPrecio(),
                                             c.getClase().getFechaInicio(),
                                             c.getClase().getFechaFin())
                                     ))
                              .collect(Collectors.toList());
        } catch(PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public byte[] obtenerBytesContenido(ContenidoViejoDTO contenido) throws NegocioException {
        Contenido c = new Contenido(
                 (Integer) contenido.getId(),
                 contenido.getNombre(),
                 contenido.getAutor(),
                 contenido.getFechaHora(),
                 new Clase(
                         contenido.getClase().getCodigo(),
                         contenido.getClase().getNombre(),
                         contenido.getClase().getDias(),
                         contenido.getClase().getHoraInicio(),
                         contenido.getClase().getHoraFin(), 
                         contenido.getClase().getMaestro(),
                         contenido.getClase().getPrecio(),
                         contenido.getClase().getFechaInicio(),
                         contenido.getClase().getFechaFin())
        );
        
        try {
            byte[] datos = this.contenidoDAO.obtenerBytesContenido(c);
            
            return datos;
        } catch(PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    
    
    
}
