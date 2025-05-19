/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocio.BOs;

import DTOs.GestionarClases.AulaClaseDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.AulaClase;
import Entidades.Clase;
import Excepciones.PersistenciaException;
import GestionarClasesPersistencia.AulaClaseDAO;
import GestionarClasesPersistencia.IAulaClaseDAO;
import com.mycompany.negocio.InterfazBO.IAulaBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public class AulaBO implements IAulaBO {

    private IAulaClaseDAO aulaDAO;

    public AulaBO(IAulaClaseDAO aulaDAO) {
        this.aulaDAO = aulaDAO;
    }

    @Override
    public boolean validarDisponibilidadHorarioAula(NuevaClaseDTO nuevaClase, List<Clase> clasesPresencialesAula) throws NegocioException {
        List<DayOfWeek> diasClase = nuevaClase.getDiasClase();
        LocalDate fechaInicioClase = nuevaClase.getFechaInicio();
        LocalDate fechaFinClase = nuevaClase.getFechaFin();
        LocalTime horaInicioNueva = nuevaClase.getHoraInicio();
        LocalTime horaFinNueva = nuevaClase.getHoraFin();

        for (Clase claseExistente : clasesPresencialesAula) {
            // Si no hay cruce de fechas, continuar
            if (fechaFinClase.isBefore(claseExistente.getFechaInicio()) || fechaInicioClase.isAfter(claseExistente.getFechaFin())) {
                continue;
            }

            // Verificar si hay al menos un día en común
            List<DayOfWeek> diasExistente = claseExistente.getDias();
            boolean hayDiaComun = diasClase.stream().anyMatch(diasExistente::contains);
            if (!hayDiaComun) {
                continue;
            }

            // Verificar si los horarios se traslapan
            LocalTime horaInicioExistente = claseExistente.getHoraInicio();
            LocalTime horaFinExistente = claseExistente.getHoraFin();

            boolean hayTraslapeHorario
                    = !horaFinNueva.isBefore(horaInicioExistente)
                    && !horaInicioNueva.isAfter(horaFinExistente);

            if (hayTraslapeHorario) {
                throw new NegocioException("Conflicto con la clase existente: " + claseExistente.getNombre());
            }
        }

        return true;
    }

    @Override
    public List<AulaClaseDTO> obtenerListaAulas() {
        List<AulaClaseDTO> aulasDTO = new ArrayList<>();
        List<AulaClase> aulas = aulaDAO.obtenerAulas();
        for (AulaClase aula : aulas) {
            AulaClaseDTO maestroDTO = convertirAulaDTO(aula);
            aulasDTO.add(maestroDTO);
        }
        return aulasDTO;

    }

    @Override
    public void agregarClasePresencial(Clase clase) throws NegocioException {
        try {
            aulaDAO.agregarClasePresencial(clase);
        } catch (PersistenciaException ex) {
            throw new NegocioException("el Id de la clase no es valido");
        }
    }

    @Override
    public AulaClaseDTO convertirAulaDTO(AulaClase aulaClase) {

        ObjectId id = aulaClase.getId();
        String idAula = id.toHexString();
        AulaClaseDTO aulaDTO = new AulaClaseDTO(idAula, aulaClase.getNombreAula());
        return aulaDTO;
    }

    public AulaClase buscarAulaClaseID(AulaClase aulaclase) throws NegocioException {
        try {
            aulaDAO.buscarClase(aulaclase);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        return null;
    }
}
