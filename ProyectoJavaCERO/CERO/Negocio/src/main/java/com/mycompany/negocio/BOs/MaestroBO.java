/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocio.BOs;

import DTOs.GestionarClases.MaestroDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.Clase;
import Entidades.Maestro;
import Excepciones.PersistenciaException;
import GestionarClasesPersistencia.MaestrosDAO;
import com.mycompany.negocio.InterfazBO.IMaestroBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import GestionarClasesPersistencia.IMaestrosDAO;

/**
 *
 * @author Jack Murrieta
 */
public class MaestroBO implements IMaestroBO {

    private IMaestrosDAO maestroDAO;

    public MaestroBO(IMaestrosDAO maestroDAO) {
        this.maestroDAO = maestroDAO;
    }

    @Override
    public boolean validarDisponibilidadHorarioMaestro(NuevaClaseDTO nuevaClase, List<Clase> clasesImpartidas) throws NegocioException {

        List<DayOfWeek> diasClase = nuevaClase.getDiasClase();
        LocalDate fechaInicioClase = nuevaClase.getFechaInicio();
        LocalDate fechaFinClase = nuevaClase.getFechaFin();
        LocalTime horaInicioNueva = nuevaClase.getHoraInicio();
        LocalTime horaFinNueva = nuevaClase.getHoraFin();

        for (Clase claseExistente : clasesImpartidas) {
            // Verificar cruce de fechas
            if (fechaFinClase.isBefore(claseExistente.getFechaInicio()) || fechaInicioClase.isAfter(claseExistente.getFechaFin())) {
                continue;
            }

            // Verificar si hay días en común
            List<DayOfWeek> diasExistente = claseExistente.getDias();
            boolean hayDiaComun = diasClase.stream().anyMatch(diasExistente::contains);
            if (!hayDiaComun) {
                continue;
            }

            // Verificar traslape de horarios
            LocalTime horaInicioExistente = claseExistente.getHoraInicio();
            LocalTime horaFinExistente = claseExistente.getHoraFin();

            boolean hayTraslapeHorario
                    = !horaFinNueva.isBefore(horaInicioExistente)
                    && !horaInicioNueva.isAfter(horaFinExistente);

            if (hayTraslapeHorario) {
                throw new NegocioException("El maestro ya tiene asignada la clase: " + claseExistente.getNombre());
            }
        }

        return true;

    }

    @Override
    public List<MaestroDTO> obtenerListaMaestros() {
        List<MaestroDTO> maestrosDTO = new ArrayList<>();
        List<Maestro> maestros = maestroDAO.obtenerMaestros();
        for (Maestro maestro : maestros) {
            MaestroDTO maestroDTO = convertirMaestroDTO(maestro);
            maestrosDTO.add(maestroDTO);
        }
        return maestrosDTO;

    }

    @Override
    public MaestroDTO convertirMaestroDTO(Maestro maestro) {
        String nombreCompleto = maestro.getNombre() + "" + maestro.getApellidoPaterno() + "" + maestro.getApellidoMaterno();
        String idMaestro = maestro.getIdString();
        MaestroDTO maestroDTO = new MaestroDTO(idMaestro, nombreCompleto);
        return maestroDTO;
    }

    @Override
    public Maestro buscarMaestroID(String idMaestro) throws NegocioException {
        try {
            return maestroDAO.buscarMaestro(idMaestro);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public List<Clase> obtenerClasesImpartidadMaestro(String idMaestro) {
        List<Clase> clasesImpartidasMaestro = maestroDAO.obtenerClasesImpartidas(idMaestro);
        return clasesImpartidasMaestro;
    }

}
