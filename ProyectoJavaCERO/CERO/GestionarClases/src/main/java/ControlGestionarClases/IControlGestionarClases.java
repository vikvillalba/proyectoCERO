/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ControlGestionarClases;

import DTOs.GestionarClases.AulaClaseDTO;
import DTOs.GestionarClases.ClaseAdminDTO;
import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.MaestroDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.Clase;
import Exceptions.GestionarClasesException;
import com.mycompany.dtos.ClaseDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public interface IControlGestionarClases {
    
    public List<ClaseListaDTO> buscarClaseListaNombre(String nombreClase);
    public void eliminarClase(ClaseListaDTO codigoClase);
    public void registrarNuevaClase(NuevaClaseDTO nuevaClase)throws GestionarClasesException;
    public List<AulaClaseDTO> obtenerListasAulas();
    public List<MaestroDTO> obtenerListaMaestros();
    public void validarDatosClase(ClaseAdminDTO nuevaClase) throws GestionarClasesException;
    public void editarClase(EditarClaseDTO editarClase)throws GestionarClasesException;
    public List<ClaseListaDTO> buscarClasesActivas() throws GestionarClasesException;

    public List<ClaseListaDTO> buscarClasesInactivas();
    public List<ClaseListaDTO> buscarClasesExistentes();
    public void validarNombreClase(String nombre)throws GestionarClasesException;
    public boolean validarLapsoHoras(LocalTime horaInicio, LocalTime horafin)throws GestionarClasesException;
    public boolean validarLapsoFechas(LocalDate fechaInicio, LocalDate fechaFin)throws GestionarClasesException;
    
    public EditarClaseDTO obtenerClaseLista(ClaseListaDTO clase);
    public boolean validarDisponibilidadHorarioMaestro(ClaseAdminDTO nuevaClase, List<Clase> clasesImpartidasMaestro) throws GestionarClasesException;
    public boolean validarDisponibilidadHorarioAula(ClaseAdminDTO nuevaClase, List<Clase> clasesPresencialesAula) throws GestionarClasesException;
    
    //buscar clase por codigo
    public ClaseDTO buscarClaseCodigo(Integer codigo);
    public void validarInactivaClase(EditarClaseDTO clase) throws GestionarClasesException;
    public void validarCapacidadAlumnos(EditarClaseDTO clase) throws GestionarClasesException;
}
