package implementaciones;

import DAOs.IContenidoDAO;
import Entidades.Clase;
import Entidades.Contenido;
import Excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author daniel
 */
public class ContenidoDAO implements IContenidoDAO {
    
    private List<Contenido> contenidos = new ArrayList<>();
    private Integer id = 1;

    @Override
    public Contenido registrarContenido(Contenido contenido) throws PersistenciaException {
        try {
            contenido.setId(id);
            this.contenidos.add(contenido);
            
            return this.contenidos.getLast();
        } catch(Exception e) {
            throw new PersistenciaException("No se pudo registrar el Contenido.");
        } finally {
            this.id++;
        }
    }

    @Override
    public boolean eliminarContenido(Contenido contenido) throws PersistenciaException {
        try {
            boolean exito = this.contenidos.removeIf(c -> c.getId() == contenido.getId());
            
            return exito;
        } catch(Exception e) {
            throw new PersistenciaException("No se pudo eliminar el Contenido.");
        }
    }

    @Override
    public List<Contenido> obtenerListaContenidos(String nombre, String autor, LocalDateTime fechaHora, Clase clase) throws PersistenciaException {
        try {
            List<Contenido> contenidos = this.contenidos.stream()
                                                        .filter(c -> c.getNombre() == nombre &&
                                                                     c.getAutor() == autor &&
                                                                     c.getFechaHora() == fechaHora &&
                                                                     c.getClase().getId()== clase.getId())
                                                        .collect(Collectors.toList());
            
            return contenidos;
        } catch(Exception e) {
            throw new PersistenciaException("No se pudo registrar el Contenido.");
        }
    }

    @Override
    public byte[] obtenerBytesContenido(Contenido contenido) throws PersistenciaException {
        try {
            byte[] datos = this.contenidos.stream()
                                          .filter(c -> c.getId() == contenido.getId())
                                          .findFirst()
                                          .get()
                                          .getContenido();
                       
            return datos;
        } catch(Exception e) {
            throw new PersistenciaException("No se encontraron los datos del Contenido.");
        }
    }
}
