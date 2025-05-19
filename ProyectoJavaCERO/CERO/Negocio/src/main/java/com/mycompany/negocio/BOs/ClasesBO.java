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
//import Mapper.ClaseMapper;
import Mapper.IClaseMapper;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.negocio.InterfazBO.IAulaBO;
import com.mycompany.negocio.InterfazBO.IClasesBO;
import com.mycompany.negocio.InterfazBO.IMaestroBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victoria
 */
public class ClasesBO implements IClasesBO {

    private IClasesDAO clasesDAO;
    private IClaseMapper claseMapper;
    private IAulaBO aulaBO;
    private IMaestroBO maestroBO;

    public ClasesBO(IClasesDAO clasesDAO, IAulaBO aulaBO, IMaestroBO maestroBO) {
        this.clasesDAO = clasesDAO;
//        this.claseMapper = new ClaseMapper();
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
                    clase.getNombreMaestro(),
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
                    clase.getNombreMaestro(),
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
    @Override
    public List<Clase> obtenerListaClasesMaestro(MaestroDTO maestro) throws NegocioException {
        String id = maestro.getId();
        Maestro maestroEntity;
        try {
            maestroEntity = maestroBO.buscarMaestroID(id);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getMessage());
        }
        List<Clase> clasesEncontradas = clasesDAO.obtenerListaClasesMaestro(maestroEntity);
        return clasesEncontradas;

    }

    @Override
    public List<Clase> obtenerListaClasesAula(AulaClaseDTO aula) throws NegocioException {

        String id = aula.getIdAula();
        AulaClase aulaEntity;
        try {
            aulaEntity = aulaBO.buscarAulaClaseID(id);
        } catch (NegocioException ex) {
            throw new NegocioException(ex.getMessage());
        }
        List<Clase> clasesEncontradas = clasesDAO.obtenerListaClasesAula(aulaEntity);
        return clasesEncontradas;

    }

    //REGISTRAR NUEVA CLASE METODO
    @Override
    public void registrarNuevaClase(NuevaClaseDTO nuevaClase) throws NegocioException {
        String idAula = nuevaClase.getAula().getIdAula();
        String idMaestro = nuevaClase.getMaestro().getId();

        Maestro maestroEncontrado = maestroBO.buscarMaestroID(idMaestro);
        AulaClase aulaClase = aulaBO.buscarAulaClaseID(idAula);

        Clase clase = claseMapper.convertirClaseEntidad(nuevaClase, maestroEncontrado, aulaClase);
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
        List<Clase> clasesActivas = clasesDAO.obtenerClasesActivas();
        List<ClaseListaDTO> clasesDTO = new ArrayList<>();

        for (Clase clase : clasesActivas) {
            Maestro maestro = null;
            AulaClase aula = null;

            if (clase.getIdMaestroString() != null) {
                maestro = maestroBO.buscarMaestroObjectId(clase.getIdMaestroString());
            }

            if (clase.getIdAulaString() != null) {
                aula = aulaBO.buscarAulaClaseObjectId(clase.getIdAulaString());
            }

            ClaseListaDTO dto = claseMapper.convertirClaseListaDTO(clase, maestro, aula);
            clasesDTO.add(dto);
        }

        return clasesDTO;
    }

    @Override
    public List<ClaseListaDTO> buscarClasesInactivas() {
        List<Clase> clasesInactivas = clasesDAO.obtenerClasesInactivas();
        List<ClaseListaDTO> clasesDTO = new ArrayList<>();

        for (Clase clase : clasesInactivas) {
            Maestro maestro = null;
            AulaClase aula = null;

            if (clase.getIdMaestroString() != null) {
                maestro = maestroBO.buscarMaestroObjectId(clase.getIdMaestroString());
            }

            if (clase.getIdAulaString()!= null) {
                aula = aulaBO.buscarAulaClaseObjectId(clase.getIdAulaString());
            }

            ClaseListaDTO dto = claseMapper.convertirClaseListaDTO(clase, maestro, aula);
            clasesDTO.add(dto);
        }

        return clasesDTO;
    }

    @Override
    public List<ClaseListaDTO> buscarClasesExistentes() {
        List<Clase> clases = clasesDAO.obtenerClases();
        List<ClaseListaDTO> clasesDTO = new ArrayList<>();

        for (Clase clase : clases) {
            Maestro maestro = null;
            AulaClase aula = null;

            if (clase.getIdMaestroString()!= null) {
                maestro = maestroBO.buscarMaestroObjectId(clase.getIdMaestroString());
            }

            if (clase.getIdAulaString()!= null) {
                aula = aulaBO.buscarAulaClaseObjectId(clase.getIdAulaString());
            }

            ClaseListaDTO dto = claseMapper.convertirClaseListaDTO(clase, maestro, aula);
            clasesDTO.add(dto);
        }

        return clasesDTO;
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
        List<ClaseListaDTO> clasesDTO = new ArrayList<>();

        for (Clase clase : clasesNombre) {
            Maestro maestro = null;
            AulaClase aula = null;

            if (clase.getIdMaestroString()!= null) {
                maestro = maestroBO.buscarMaestroObjectId(clase.getIdMaestroString());
            }

            if (clase.getIdAulaString()!= null) {
                aula = aulaBO.buscarAulaClaseObjectId(clase.getIdAulaString());
            }

            ClaseListaDTO dto = claseMapper.convertirClaseListaDTO(clase, maestro, aula);
            clasesDTO.add(dto);
        }

        return clasesDTO;
    }

    @Override
    public EditarClaseDTO obtenerClaseListaDTO(ClaseListaDTO clase) {
        Clase claseEntity = clasesDAO.buscarClaseCodigoInteger(clase.getCodigo());
        Maestro maestro = maestroBO.buscarMaestroObjectId(claseEntity.getIdMaestroString());
        AulaClase aula = aulaBO.buscarAulaClaseObjectId(claseEntity.getIdAulaString());
        return claseMapper.convertirEditarClase(claseEntity, maestro, aula);
    }

}
