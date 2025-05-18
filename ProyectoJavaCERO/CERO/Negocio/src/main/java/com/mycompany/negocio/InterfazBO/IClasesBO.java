package com.mycompany.negocio.InterfazBO;

import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Maestro;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author victoria
 */
public interface IClasesBO {
    public List<ClaseDTO> obtenerClasesNombre(String nombreClase) throws NegocioException;
    public List<ClaseDTO> obtenerClases()throws NegocioException;
    public Integer obtenerLimiteFaltas(ClaseDTO clase);
    
    //METODOS CU_GESTIONAR CLASES 
    public List<Clase> obtenerListaClasesMaestro(Maestro maestro);

    public List<Clase> obtenerListaClasesAula(AulaClase aula);

    //
    public ClaseListaDTO buscarClase(String nombreClase);

    public void registrarNuevaClase(NuevaClaseDTO nuevaClase);

    public void validarDatosClase(NuevaClaseDTO nuevaClase);

    public void editarClase(EditarClaseDTO editarClse);

    public List<ClaseListaDTO> buscarClasesActivas();

    public List<ClaseListaDTO> buscarClasesInactivas();

    public List<ClaseListaDTO> buscarClasesExistentes();

    public void inactivarClase(EditarClaseDTO clase);

    public boolean validarLapsoHoras(LocalTime horaInicio, LocalTime horaFin);

    public boolean validarLapsoFechas(LocalDate fechaInicio, LocalDate fechaFin);

    public Clase validarExistenciaClase(NuevaClaseDTO nuevaClase);

    public int obtenerCuposDisponibles(int cantidadInscritos, int capacidadClase);

    public boolean validarCapacidadMaxMenorCantidadInscritos(int capacidad, int cantidadInscritos);

    public void eliminarClase(EditarClaseDTO clase);

    
    
}
