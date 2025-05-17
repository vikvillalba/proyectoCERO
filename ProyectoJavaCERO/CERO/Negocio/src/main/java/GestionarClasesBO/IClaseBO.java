/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GestionarClasesBO;

import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Maestro;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public interface IClaseBO {
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
