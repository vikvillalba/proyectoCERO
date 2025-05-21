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
import DTOs.GestionarClases.AlumnoClaseDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.negocio.InterfazBO.IAulaBO;
import com.mycompany.negocio.InterfazBO.IMaestroBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.negocio.InterfazBO.IClasesBO;

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
        this.claseMapper = new ClaseMapper();
        this.aulaBO = aulaBO;
        this.maestroBO = maestroBO;
    }

    @Override
    public List<ClaseDTO> obtenerClasesNombre(String nombreClase) throws NegocioException {
        List<Clase> clases = this.clasesDAO.buscarNombreClases(nombreClase);
        if (clases == null || clases.isEmpty()) {
            throw new NegocioException("No se encontraron clases relacionadas.");
        }
        List<ClaseDTO> clasesObtenidas = new ArrayList<>();
        for (Clase clase : clases) {
            Maestro maestro = maestroBO.buscarMaestroID(clase.getIdMaestroString());
            ClaseDTO claseDTO = new ClaseDTO(
                    clase.getCodigo(),
                    clase.getNombre(),
                    clase.getDias(),
                    clase.getHoraInicio(),
                    clase.getHoraFin(),
                    maestro.getNombreCompleto(),
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
            Maestro maestro = maestroBO.buscarMaestroID(clase.getIdMaestroString());
            ClaseDTO claseDTO = new ClaseDTO(
                    clase.getCodigo(),
                    clase.getNombre(),
                    clase.getDias(),
                    clase.getHoraInicio(),
                    clase.getHoraFin(),
                    maestro.getNombreCompleto(),
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
        Clase claseReal = this.clasesDAO.buscarClaseCodigoInteger(clase.getCodigo());
        return claseReal.getLIMITE_FALTAS();
    }

    //METODOS CU_GESTIONAR CLASES
    //REGISTRAR NUEVA CLASE METODO
    @Override
    public void registrarNuevaClase(NuevaClaseDTO nuevaClase) throws NegocioException {
        if (nuevaClase == null) {
            throw new NegocioException("La clase no puede ser nula.");
        }

        AulaClase aulaClase = null;

        // Solo buscar el aula si la modalidad es PRESENCIAL
        if ("Presencial".equalsIgnoreCase(nuevaClase.getModalidad())) {
            if (nuevaClase.getAula() == null || nuevaClase.getAula().getIdAula() == null) {
                throw new NegocioException("Una clase presencial requiere un aula asignada.");
            }
            String idAula = nuevaClase.getAula().getIdAula();
            aulaClase = aulaBO.buscarAulaClaseID(idAula);
            if (aulaClase == null) {
                throw new NegocioException("No se encontró el aula especificada.");
            }
        }

        // Validar maestro
        if (nuevaClase.getMaestro() == null || nuevaClase.getMaestro().getId() == null) {
            throw new NegocioException("Debe seleccionarse un maestro.");
        }

        String idMaestro = nuevaClase.getMaestro().getId();
        Maestro maestroEncontrado = maestroBO.buscarMaestroID(idMaestro);
        if (maestroEncontrado == null) {
            throw new NegocioException("No se encontró el maestro especificado.");
        }

        // Convertir DTO a entidad
        Clase clase = claseMapper.convertirClaseEntidad(nuevaClase, maestroEncontrado, aulaClase);

        // Registrar en la base de datos
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
        if (clasesActivas.isEmpty() || clasesActivas == null) {
            return new ArrayList<>();
        }
        List<ClaseListaDTO> clases = new ArrayList<>();
        for (Clase clase : clasesActivas) {
            try {
                Maestro maestro = maestroBO.buscarMaestroID(clase.getIdMaestroString());
                AulaClase aula = aulaBO.buscarAulaClaseID(clase.getIdAulaString());

                clases.add(claseMapper.convertirClaseListaDTO(clase, maestro, aula));
            } catch (NegocioException ex) {
                throw new NegocioException(ex.getMessage());
            }
        }

        return clases;
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
