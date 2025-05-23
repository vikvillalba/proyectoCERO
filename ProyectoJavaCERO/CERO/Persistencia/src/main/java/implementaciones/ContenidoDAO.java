package implementaciones;

import Entidades.Clase;
import Entidades.Contenido;
import Excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import DAOs.IContenidoDAO;

/**
 *
 * @author daniel
 */
public class ContenidoDAO implements IContenidoDAO {
    
    private List<Contenido> contenidos = new ArrayList<>();
    private Integer id;

    @Override
    public Contenido registrarContenido(Contenido contenido) throws PersistenciaException {
        try {
//            contenido.setId(id);
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
                                                        .filter(c -> 
                                                               (nombre == null || c.getNombre().equals(nombre)) &&
                                                               (autor == null || c.getAutor().equals(autor)) &&
                                                               (fechaHora == null || c.getFechaHora().equals(fechaHora)) &&
                                                               (clase == null || c.getCodigoClase()== clase.getCodigo()))
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
