package com.mycompany.negocio.BOs;

import Entidades.Clase;
import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.AulaClase;
import Entidades.Maestro;
import GestionarClasesPersistencia.IClaseDAO;
import Mapper.ClaseMapper;
import Mapper.IClaseMapper;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.negocio.InterfazBO.IClasesBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victoria
 */
public class ClasesBO implements IClasesBO {

    private IClaseDAO clasesDAO;
    private IClaseMapper claseMapper;

    public ClasesBO(IClaseDAO clasesDAO) {
        this.clasesDAO = clasesDAO;
        this.claseMapper = new ClaseMapper();
    }

    @Override
    public List<ClaseDTO> obtenerClasesNombre(String nombreClase) throws NegocioException {
        List<Clase> clases = this.clasesDAO.obtenerClasesPorNombre(nombreClase);
        if (clases == null || clases.isEmpty()) {
            throw new NegocioException("No se encontraron clases relacionadas.");
        }
        List<ClaseDTO> clasesObtenidas = new ArrayList<>();
        for (Clase clase : clases) {
            ClaseDTO claseDTO = new ClaseDTO(
                    clase.getCodigo(),
                    clase.getNombre(),
                    clase.getDias(),
                    clase.getHoraInicio(),
                    clase.getHoraFin(),
                    clase.getMaestro().getNombre(),
                    clase.getPrecio(),
                    clase.getFechaInicio(),
                    clase.getFechaFin()
            );
            clasesObtenidas.add(claseDTO);
        }
        return clasesObtenidas;
    }

    @Override
    public List<ClaseDTO> obtenerClases() throws NegocioException {
        List<Clase> clases = this.clasesDAO.obtenerClases();
        if (clases == null || clases.isEmpty()) {
            throw new NegocioException("No se encontraron clases relacionadas.");
        }
        List<ClaseDTO> clasesObtenidas = new ArrayList<>();
        for (Clase clase : clases) {
            ClaseDTO claseDTO = new ClaseDTO(
                    clase.getCodigo(),
                    clase.getNombre(),
                    clase.getDias(),
                    clase.getHoraInicio(),
                    clase.getHoraFin(),
                    clase.getMaestro().getNombre(),
                    clase.getPrecio(),
                    clase.getFechaInicio(),
                    clase.getFechaFin()
            );
            clasesObtenidas.add(claseDTO);
        }
        return clasesObtenidas;
    }

    @Override
    public Integer obtenerLimiteFaltas(ClaseDTO clase) {
        Clase claseReal = this.clasesDAO.buscarClase(clase.getCodigo());
        return claseReal.getLIMITE_FALTAS();
    }

    
    //METODOS CU_GESTIONAR CLASES
    @Override
    public List<Clase> obtenerListaClasesMaestro(Maestro maestro) {
        return clasesDAO.obtenerListaClasesMaestro(maestro);
    }

    @Override
    public List<Clase> obtenerListaClasesAula(AulaClase aula) {
        return clasesDAO.obtenerListaClasesAula(aula);
    }

    @Override
    public void registrarNuevaClase(NuevaClaseDTO nuevaClase) {
        //validar existencia de una clase similar

        //validar que los horarios no se empalmen con los de las clases presenciales y clases impartidas del maestro seleccionado
        //registrar clase
    }

    @Override
    public void validarDatosClase(NuevaClaseDTO nuevaClase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editarClase(EditarClaseDTO editarClse) {
        //validar que la horaFin no sea menor que la hora inicio ni la fecha fin menor que la fechaInicio
    }

    @Override
    public List<ClaseListaDTO> buscarClasesActivas() {
        List<Clase> clases = clasesDAO.obtenerClases();
        List<ClaseListaDTO> clasesActivas = new ArrayList<>();
        for (Clase clase : clases) {
            if (clase.isActiva()) {
                ClaseListaDTO claseListaDTO = claseMapper.convertirClaseListaDTO(clase);
                clasesActivas.add(claseListaDTO);
            }
        }
        return clasesActivas;
    }

    @Override
    public List<ClaseListaDTO> buscarClasesInactivas() {
        List<Clase> clases = clasesDAO.obtenerClases();
        List<ClaseListaDTO> clasesInactivas = new ArrayList<>();
        for (Clase clase : clases) {
            if (clase.isActiva() == false) {
                ClaseListaDTO claseListaDTO = claseMapper.convertirClaseListaDTO(clase);
                clasesInactivas.add(claseListaDTO);
            }
        }
        return clasesInactivas;
    }

    @Override
    public List<ClaseListaDTO> buscarClasesExistentes() {
        List<Clase> clases = clasesDAO.obtenerClases();
        List<ClaseListaDTO> clasesExistentes = new ArrayList<>();
        for (Clase clase : clases) {
            ClaseListaDTO claseListaDTO = claseMapper.convertirClaseListaDTO(clase);
            clasesExistentes.add(claseListaDTO);

        }
        return clasesExistentes;
    }

    @Override
    public void inactivarClase(EditarClaseDTO clase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean validarLapsoHoras(LocalTime horaInicio, LocalTime horaFin) throws NegocioException {
        //horaFin no sea menor que horaInicio
        //horaInicio no sobrePase HoraFin
        if (horaInicio == null || horaFin == null) {
            throw new NegocioException("Error en el lapso de Horas");
        }

        if (horaFin.isBefore(horaInicio)) {
            throw new NegocioException("Hora fin es menor que la hora inicio");
        }
        
        Duration duracion = Duration.between(horaInicio, horaFin);
        if (duracion.toMinutes() < 30) {
            throw new NegocioException("Debe de tener una duracion minima de 30 minutos");
        }

        return true;
    }


    @Override
    public boolean validarLapsoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        //FechaInicio no sobrePase a la fechaFin
        //fechaFin que no sea menor a la fecha Inicio
    }

    @Override
    public Clase validarExistenciaClase(NuevaClaseDTO nuevaClase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int obtenerCuposDisponibles(int cantidadInscritos, int capacidadClase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean validarCapacidadMaxMenorCantidadInscritos(int capacidad, int cantidadInscritos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarClase(EditarClaseDTO clase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ClaseListaDTO> buscarClasesListaNombre(String nombreClase) {
        List<Clase> clasesNombre = clasesDAO.obtenerClasesPorNombre(nombreClase);
        List<ClaseListaDTO> clasesEncontradas = new ArrayList<>();
        for (Clase claseNombre : clasesNombre) {
            ClaseListaDTO claseListaDTO = claseMapper.convertirClaseListaDTO(claseNombre);
            clasesEncontradas.add(claseListaDTO);
        }
        return clasesEncontradas;
    }

}
