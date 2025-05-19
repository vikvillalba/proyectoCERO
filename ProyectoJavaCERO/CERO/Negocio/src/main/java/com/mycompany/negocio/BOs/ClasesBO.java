package com.mycompany.negocio.BOs;

import DAOs.IClasesDAO;
import DTOs.GestionarClases.AulaClaseDTO;
import Entidades.Clase;
import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.MaestroDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.AulaClase;
import Entidades.Maestro;
import Mapper.ClaseMapper;
import Mapper.IClaseMapper;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.negocio.InterfazBO.IAulaBO;
import com.mycompany.negocio.InterfazBO.IClasesBO;
import com.mycompany.negocio.InterfazBO.IMaestroBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author victoria
 */
public class ClasesBO implements IClasesBO {

    private IClasesDAO clasesDAO;
    private IClaseMapper claseMapper;
    private IAulaBO aulaBO;
    private IMaestroBO maestroBO;

    public ClasesBO(IClasesDAO clasesDAO,IAulaBO aulaBO,IMaestroBO maestroBO) {
        this.clasesDAO = clasesDAO;
        this.claseMapper = new ClaseMapper();
        this.aulaBO = aulaBO;
        this.maestroBO = maestroBO;
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
        Clase claseReal = this.clasesDAO.buscarClase(clase.getId());
        return claseReal.getLIMITE_FALTAS();
    }

        //METODOS CU_GESTIONAR CLASES
    public List<Clase> obtenerListaClasesMaestro(MaestroDTO maestro) {
        String id = maestro.getId();
        ObjectId idMaestro = new ObjectId(id);
        Maestro maestroEntity = new Maestro();
        maestroEntity.setId(idMaestro);
        List<Clase> clasesEncontradas = clasesDAO.obtenerListaClasesMaestro(maestroEntity);
        return clasesEncontradas;

    }

    public List<Clase> obtenerListaClasesAula(AulaClaseDTO aula) {

        String id = aula.getIdAula();
        ObjectId idAula = new ObjectId(id);
        AulaClase aulaEntity = new AulaClase();
        aulaEntity.setId(idAula);
        List<Clase> clasesEncontradas = clasesDAO.obtenerListaClasesAula(aulaEntity);
        return clasesEncontradas;

    }

    @Override
    public void registrarNuevaClase(NuevaClaseDTO nuevaClase) throws NegocioException {
        AulaClase aulaEncontrada = null;
        if (nuevaClase.getAula() != null) {
            String id = nuevaClase.getAula().getIdAula();
            ObjectId idAula = new ObjectId(id);
            AulaClase aulaEntity = new AulaClase();
            aulaEntity.setId(idAula);
            AulaClase aluaEncontrada = aulaBO.buscarAulaClaseID(aulaEntity);
        }

        String id = nuevaClase.getMaestro().getId();
        ObjectId idMaestro = new ObjectId(id);
        Maestro maestroEntity = new Maestro();
        maestroEntity.setId(idMaestro);
        Maestro maestroEncontrado = maestroBO.buscarMaestroID(maestroEntity);

        Clase clase = claseMapper.convertirClaseEntidad(nuevaClase, maestroEncontrado, aulaEncontrada);
        clasesDAO.registrarNuevaClase(clase);
    }


    @Override
    public void editarClase(EditarClaseDTO editarClase) {
        Clase claseEncontrada = clasesDAO.buscarClaseCodigoInteger(editarClase.getCodigo());
        //actualiza activa
        claseEncontrada.setActiva(editarClase.isActiva());
        //actualiza la capacidad
        claseEncontrada.setCapacidadAlumnos(editarClase.getCapacidad());
        //actualiza la hora fin
        claseEncontrada.setFechaFin(editarClase.getFechaFin());
        //actualiza la fechaFin
        claseEncontrada.setHoraFin(editarClase.getHoraFin());
        
        clasesDAO.editarClase(claseEncontrada);
        
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
    public int obtenerCuposDisponibles(int cantidadInscritos, int capacidadClase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean validarCapacidadMaxMenorCantidadInscritos(int capacidad, int cantidadInscritos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarClase(ClaseListaDTO clase) {
        Clase claseEncontrada = clasesDAO.buscarClaseCodigoInteger(clase.getCodigo());
        if (claseEncontrada != null) {
            clasesDAO.eliminarClase(claseEncontrada);
        }
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
    
    public EditarClaseDTO obtenerClaseListaDTO(ClaseListaDTO clase){
        Clase claseEntity = clasesDAO.buscarClaseCodigoInteger(clase.getCodigo());
        EditarClaseDTO claseDTO = claseMapper.convertirEditarClase(claseEntity);
        return claseDTO;
    }
    
}
