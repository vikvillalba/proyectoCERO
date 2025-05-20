package com.mycompany.negocio.InterfazBO;

import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import DTOs.GestionarClases.AlumnoClaseDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IClasesBO {

    public List<ClaseDTO> obtenerClasesNombre(String nombreClase) throws NegocioException;

    public List<ClaseDTO> obtenerClases() throws NegocioException;

    public Integer obtenerLimiteFaltas(ClaseDTO clase);

    //METODOS CU_GESTIONAR CLASES 

    public List<ClaseListaDTO> buscarClasesListaNombre(String nombreClase);

    public void registrarNuevaClase(NuevaClaseDTO nuevaClase) throws NegocioException;

    public void editarClase(EditarClaseDTO editarClse);

    public List<ClaseListaDTO> buscarClasesActivas() throws NegocioException;

    public List<ClaseListaDTO> buscarClasesInactivas();

    public List<ClaseListaDTO> buscarClasesExistentes();

    public int obtenerCuposDisponibles(int cantidadInscritos, int capacidadClase);

    public boolean validarCapacidadMaxMenorCantidadInscritos(int capacidad, int cantidadInscritos);

    public void eliminarClase(ClaseListaDTO clase);

    public EditarClaseDTO obtenerClaseListaDTO(ClaseListaDTO clase);
    

}
