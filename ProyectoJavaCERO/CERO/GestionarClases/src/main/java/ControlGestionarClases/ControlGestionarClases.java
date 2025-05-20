/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlGestionarClases;

import DTOs.GestionarClases.AulaClaseDTO;
import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.MaestroDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.Clase;
import Exceptions.GestionarClasesException;
import com.mycompany.negocio.Fabricas.FabricaObjetosNegocio;
import com.mycompany.negocio.InterfazBO.IClasesBO;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.mycompany.negocio.InterfazBO.*;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Jack Murrieta
 */
public class ControlGestionarClases implements IControlGestionarClases {

    private IClasesBO clasesBO;
    private IMaestroBO maestroBO;
    private IAulaBO aulaBO;

    public ControlGestionarClases() {
        this.clasesBO = FabricaObjetosNegocio.obtenerClasesBO();
        this.maestroBO = FabricaObjetosNegocio.obtenerMaestroBO();
        this.aulaBO = FabricaObjetosNegocio.obtenerAulaBO();
    }

    //obtiene las listas en claseListaDTO
    @Override
    public List<ClaseListaDTO> buscarClaseListaNombre(String nombreClase) {
        return clasesBO.buscarClasesListaNombre(nombreClase);
    }

    //elimina una clase desde ClaseListaDTO
    @Override
    public void eliminarClase(ClaseListaDTO clase) {
        clasesBO.eliminarClase(clase);
    }

    //registra una nueva clase
    @Override
    public void registrarNuevaClase(NuevaClaseDTO nuevaClase) throws GestionarClasesException {
        //
        try {
            // Validación de datos generales
            validarDatosClase(nuevaClase);

            // Validación de aula (si existe)
            if (nuevaClase.getAula() != null) {
                List<Clase> clasesPresencialesAula = aulaBO.obtenerClasesPresencialesAula(nuevaClase.getAula().getIdAula());
                validarDisponibilidadHorarioAula(nuevaClase, clasesPresencialesAula);
            }

            // Validación de disponibilidad del maestro
            List<Clase> clasesImpartidasMaestro = maestroBO.obtenerClasesImpartidadMaestro(nuevaClase.getMaestro().getId());
            validarDisponibilidadHorarioMaestro(nuevaClase, clasesImpartidasMaestro);

            // Registro
            clasesBO.registrarNuevaClase(nuevaClase);

        } catch (NegocioException | IllegalArgumentException ex) {
            throw new GestionarClasesException("No se pudo registrar la clase: " + ex.getMessage());
        }
    }

    //obtiene las aula en AulaClaseDTO para seleccionarla en un combox
    @Override
    public List<AulaClaseDTO> obtenerListasAulas() {
        return aulaBO.obtenerListaAulas();
    }

    //obtiene las listas de maestros para selecciona en un combox
    @Override
    public List<MaestroDTO> obtenerListaMaestros() {
        return maestroBO.obtenerListaMaestros();
    }

    @Override
    public void validarDatosClase(NuevaClaseDTO nuevaClase) throws GestionarClasesException {
        // valida los datos de la clase como nombre
        try {
            validarNombreClase(nuevaClase.getNombreClase());
            //valida las fechas
            validarLapsoFechas(nuevaClase.getFechaInicio(), nuevaClase.getFechaFin());
            //validar lapso horas
            validarLapsoHoras(nuevaClase.getHoraInicio(), nuevaClase.getHoraFin());
        } catch (GestionarClasesException ex) {
            throw new GestionarClasesException(ex.getMessage());
        }
    }

    @Override
    public void editarClase(EditarClaseDTO editarClase) throws GestionarClasesException {

        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime horaInicio = LocalTime.parse(editarClase.getHoraInicio(), formatterHora);
        LocalDate fechaInicio = LocalDate.parse(editarClase.getFechaInicio(), formatterFecha);

        try {
            validarLapsoHoras(horaInicio, editarClase.getHoraFin());
        } catch (GestionarClasesException ex) {
            throw new GestionarClasesException(ex.getMessage());
        }
        try {
            validarLapsoFechas(fechaInicio, editarClase.getFechaFin());
        } catch (GestionarClasesException ex) {
            throw new GestionarClasesException(ex.getMessage());
        }
        
        //validar que no puedes inactivar una clase que no se ha cumplido su lapso de fecha fin 
        // no puedes inactivar una clase con inscripciones
        
        clasesBO.editarClase(editarClase);
    }
    

    @Override
    public List<ClaseListaDTO> buscarClasesActivas() throws GestionarClasesException {
        try {
            return clasesBO.buscarClasesActivas();
        } catch (NegocioException ex) {
            throw new GestionarClasesException(ex.getMessage());
        }
    }

    @Override
    public List<ClaseListaDTO> buscarClasesInactivas() {
        return clasesBO.buscarClasesInactivas();
    }

    @Override
    public List<ClaseListaDTO> buscarClasesExistentes() {
        return clasesBO.buscarClasesExistentes();
    }

    @Override
    public void validarNombreClase(String nombre) throws GestionarClasesException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new GestionarClasesException("El campo nombre está vacío o solo contiene espacios.");
        }

        if (nombre.length() < 3) {
            throw new GestionarClasesException("El nombre debe tener al menos 3 caracteres.");
        }

        if (nombre.length() > 50) {
            throw new GestionarClasesException("El nombre no debe superar los 50 caracteres.");
        }

        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 ]+$")) {
            throw new GestionarClasesException("El nombre contiene caracteres inválidos.");
        }

    }

    @Override
    public boolean validarLapsoHoras(LocalTime horaInicio, LocalTime horaFin) throws GestionarClasesException {
        //horaFin no sea menor que horaInicio
        //horaInicio no sobrePase HoraFin
        if (horaInicio == null || horaFin == null) {
            throw new GestionarClasesException("Error en el lapso de Horas");
        }

        if (horaFin.isBefore(horaInicio)) {
            throw new GestionarClasesException("Hora fin es menor que la hora inicio");
        }

        Duration duracion = Duration.between(horaInicio, horaFin);
        if (duracion.toMinutes() < 30) {
            throw new GestionarClasesException("Debe de tener una duracion minima de 30 minutos");
        }

        return true;
    }

    @Override
    public boolean validarLapsoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws GestionarClasesException {
        if (fechaInicio == null || fechaFin == null) {
            throw new GestionarClasesException("Error en el lapso de Fechas");
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new GestionarClasesException("Fecha fin es menor que la fecha inicio");
        }

        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        if (dias < 5) {
            throw new GestionarClasesException("El lapso entre fechas debe ser de al menos 5 días");
        }

        return true;
    }

    @Override
    public EditarClaseDTO obtenerClaseLista(ClaseListaDTO clase) {
        return clasesBO.obtenerClaseListaDTO(clase);
    }

    //Validar disponibilidad del aula
    @Override
    public boolean validarDisponibilidadHorarioAula(NuevaClaseDTO nuevaClase, List<Clase> clasesPresencialesAula) throws GestionarClasesException {
        List<DayOfWeek> diasClase = nuevaClase.getDiasClase();
        LocalDate fechaInicioClase = nuevaClase.getFechaInicio();
        LocalDate fechaFinClase = nuevaClase.getFechaFin();
        LocalTime horaInicioNueva = nuevaClase.getHoraInicio();
        LocalTime horaFinNueva = nuevaClase.getHoraFin();

        for (Clase claseExistente : clasesPresencialesAula) {
            LocalDate fechaInicioExistente = claseExistente.getFechaInicio();
            LocalDate fechaFinExistente = claseExistente.getFechaFin();

            // Si las fechas nuevas están completamente fuera del rango de la clase existente, continuar
            if ((fechaFinClase.isBefore(fechaInicioExistente) && fechaInicioClase.isBefore(fechaInicioExistente))
                    || (fechaInicioClase.isAfter(fechaFinExistente) && fechaFinClase.isAfter(fechaFinExistente))) {
                continue; // No hay cruce de fechas, revisar siguiente clase
            }

            // Verificar si hay días en común
            List<DayOfWeek> diasExistente = claseExistente.getDias();
            for (DayOfWeek dia : diasClase) {
                if (diasExistente.contains(dia)) {
                    // Validar traslape de horarios
                    LocalTime horaInicioExistente = claseExistente.getHoraInicio();
                    LocalTime horaFinExistente = claseExistente.getHoraFin();

                    boolean hayTraslapeHorario
                            = !horaFinNueva.isBefore(horaInicioExistente)
                            && !horaInicioNueva.isAfter(horaFinExistente);

                    if (hayTraslapeHorario) {
                        throw new GestionarClasesException("El aula tiene Conflicto con la clase '" + claseExistente.getNombre()
                                + "' el día " + dia + " entre " + horaInicioNueva + " y " + horaFinNueva
                                + ". Horario existente: " + horaInicioExistente + " a " + horaFinExistente);
                    }
                }
            }
        }

        return true; // Solo si pasó todas las validaciones
    }

    //VALIDAR DISPONIBILIDD CON EL HORARIO DEL MAESTRO
    @Override
    public boolean validarDisponibilidadHorarioMaestro(NuevaClaseDTO nuevaClase, List<Clase> clasesImpartidasMaestro) throws GestionarClasesException {

        List<DayOfWeek> diasClase = nuevaClase.getDiasClase();
        LocalDate fechaInicioClase = nuevaClase.getFechaInicio();
        LocalDate fechaFinClase = nuevaClase.getFechaFin();
        LocalTime horaInicioNueva = nuevaClase.getHoraInicio();
        LocalTime horaFinNueva = nuevaClase.getHoraFin();

        for (Clase claseExistente : clasesImpartidasMaestro) {
            LocalDate fechaInicioExistente = claseExistente.getFechaInicio();
            LocalDate fechaFinExistente = claseExistente.getFechaFin();

            // Si las fechas nuevas están completamente fuera del rango de la clase existente, continuar
            if ((fechaFinClase.isBefore(fechaInicioExistente) && fechaInicioClase.isBefore(fechaInicioExistente))
                    || (fechaInicioClase.isAfter(fechaFinExistente) && fechaFinClase.isAfter(fechaFinExistente))) {
                continue; // No hay cruce de fechas, revisar siguiente clase
            }

            // Verificar si hay días en común
            List<DayOfWeek> diasExistente = claseExistente.getDias();
            for (DayOfWeek dia : diasClase) {
                if (diasExistente.contains(dia)) {
                    // Validar traslape de horarios
                    LocalTime horaInicioExistente = claseExistente.getHoraInicio();
                    LocalTime horaFinExistente = claseExistente.getHoraFin();

                    boolean hayTraslapeHorario
                            = !horaFinNueva.isBefore(horaInicioExistente)
                            && !horaInicioNueva.isAfter(horaFinExistente);

                    if (hayTraslapeHorario) {
                        throw new GestionarClasesException("El Maestro tiene Conflicto con la clase '" + claseExistente.getNombre()
                                + "' el día " + dia + " entre " + horaInicioNueva + " y " + horaFinNueva
                                + ". Horario existente: " + horaInicioExistente + " a " + horaFinExistente);
                    }
                }
            }
        }

        return true; // Solo si pasó todas las validaciones
    }

}
