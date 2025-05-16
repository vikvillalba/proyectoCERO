package DAOs;

import Entidades.Clase;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IClasesDAO {
    
    public List<Clase> obtenerClasesPorNombre(String nombreClase);
    
    public Clase buscarClase(Integer id);
    
    public List<Clase> obtenerClases();
    
    public Integer obtenerLimiteFaltas(Clase clase);
    
}
