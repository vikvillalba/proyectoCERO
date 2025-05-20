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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public List<ClaseListaDTO> buscarClasesActivas() throws NegocioException {
        List<Clase> clasesActivas = clasesDAO.obtenerClasesActivas();
        if(clasesActivas.isEmpty() || clasesActivas == null){
            throw new NegocioException("No se encontraron clases activas");
        }
        List<ClaseListaDTO> clasesDTO = new ArrayList<>();
    }

    public List<ClaseListaDTO> buscarClasesActivasClaseListaDTO() {

        try {
            List<Clase> clasesActivas = clasesDAO.obtenerClasesActivas();
            List<ClaseListaDTO> clasesDTO = new ArrayList<>();

            for (Clase clase : clasesActivas) {
                Maestro maestro = null;
                AulaClase aula = null;

                if (clase.getIdMaestroString() != null) {
                    maestro = maestroBO.buscarMaestroID(clase.getIdMaestroString());
                }

                if (clase.getIdAulaString() != null) {
                    try {
                        aula = aulaBO.buscarAulaClaseID(clase.getIdAulaString());
                    } catch (NegocioException ex) {
                        Logger.getLogger(ClasesBO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                ClaseListaDTO dto = claseMapper.convertirClaseListaDTO(clase, maestro, aula);
                clasesDTO.add(dto);
            }

            return clasesDTO;
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<ClaseListaDTO> buscarClasesInactivas() {
        try {
            List<Clase> clasesInactivas = clasesDAO.obtenerClasesInactivas();
            List<ClaseListaDTO> clasesDTO = new ArrayList<>();

            for (Clase clase : clasesInactivas) {
                Maestro maestro = null;
                AulaClase aula = null;

                if (clase.getIdMaestroString() != null) {
                    maestro = maestroBO.buscarMaestroID(clase.getIdMaestroString());
                }

                if (clase.getIdAulaString() != null) {
                    aula = aulaBO.buscarAulaClaseID(clase.getIdAulaString());
                }

                ClaseListaDTO dto = claseMapper.convertirClaseListaDTO(clase, maestro, aula);
                clasesDTO.add(dto);
            }

            return clasesDTO;
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<ClaseListaDTO> buscarClasesExistentes() {
        try {
            List<Clase> clasesExistentes = clasesDAO.obtenerClases();
            List<ClaseListaDTO> clasesDTO = new ArrayList<>();

            for (Clase clase : clasesExistentes) {
                Maestro maestro = null;
                AulaClase aula = null;

                if (clase.getIdMaestroString() != null) {
                    maestro = maestroBO.buscarMaestroID(clase.getIdMaestroString());
                }

                if (clase.getIdAulaString() != null) {
                    aula = aulaBO.buscarAulaClaseID(clase.getIdAulaString());
                }

                ClaseListaDTO dto = claseMapper.convertirClaseListaDTO(clase, maestro, aula);
                clasesDTO.add(dto);
            }

            return clasesDTO;
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
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
        try {
            List<Clase> clasesNombre = clasesDAO.obtenerClasesPorNombre(nombreClase);
            List<ClaseListaDTO> clasesDTO = new ArrayList<>();

            for (Clase clase : clasesNombre) {
                Maestro maestro = null;
                AulaClase aula = null;

                if (clase.getIdMaestroString() != null) {
                    maestro = maestroBO.buscarMaestroID(clase.getIdMaestroString());
                }

                if (clase.getIdAulaString() != null) {
                    aula = aulaBO.buscarAulaClaseID(clase.getIdAulaString());
                }

                ClaseListaDTO dto = claseMapper.convertirClaseListaDTO(clase, maestro, aula);
                clasesDTO.add(dto);
            }

            return clasesDTO;
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public EditarClaseDTO obtenerClaseListaDTO(ClaseListaDTO clase) {
        try {
            Clase claseEntity = clasesDAO.buscarClaseCodigoInteger(clase.getCodigo());
            Maestro maestro = maestroBO.buscarMaestroID(claseEntity.getIdMaestroString());
            AulaClase aula = aulaBO.buscarAulaClaseID(claseEntity.getIdAulaString());
            return claseMapper.convertirEditarClase(claseEntity, maestro, aula);
        } catch (NegocioException ex) {
            Logger.getLogger(ClasesBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
