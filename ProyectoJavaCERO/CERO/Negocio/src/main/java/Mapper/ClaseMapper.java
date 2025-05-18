/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Maestro;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public class ClaseMapper implements IClaseMapper {

    public ClaseMapper() {
    }
    
    //Actualizar los cupos de las clases dependiend de las inscripciones
    //uso del Observer
    

    @Override
    public Clase convertirClaseEntidad(NuevaClaseDTO nuevaClase, Maestro maestro, AulaClase aula) {

        double precioDouble = nuevaClase.getPrecio();
        BigDecimal precio = BigDecimal.valueOf(precioDouble);

        Clase clase = new Clase(nuevaClase.getCodigo(),
                nuevaClase.getNombreClase(),
                maestro,
                nuevaClase.getModalidad(),
                nuevaClase.getDiasClase(),
                nuevaClase.getHoraInicio(),
                nuevaClase.getHoraFin(),
                nuevaClase.getFechaInicio(),
                nuevaClase.getFechaFin(),
                nuevaClase.getCapacidadAlumnos(),
                aula,
                precio,
                true);

        return clase;

    }

    @Override
    public ClaseListaDTO convertirClaseListaDTO(Clase clase) {

        List<DayOfWeek> dias = clase.getDias();
        String diasTexto = convertirDias(dias);

        String fechaInicio = convertirFecha(clase.getFechaInicio());
        String fechaFin = convertirFecha(clase.getFechaFin());
        String periodo = fechaInicio + "-" + fechaFin;

        String horaInicio = convertirHora(clase.getHoraInicio());
        String horaFin = convertirHora(clase.getHoraFin());
        String horaCompleta = horaInicio + "-" + horaFin;
        String horario = diasTexto + "\n" + horaCompleta;

        ClaseListaDTO claseListaDTO = new ClaseListaDTO(clase.getCodigo(),
                clase.getNombre(),
                horario,
                clase.getMaestro().getNombreCompleto(),
                clase.getCapacidadAlumnos(),
                periodo,
                clase.getAula().getNombreAula(),
                clase.isActiva());

        return claseListaDTO;
    }

    @Override
    public EditarClaseDTO convertirEditarClase(Clase clase) {
        String nombreMaestro = clase.getMaestro().getNombreCompleto();
        String aulaNombre = clase.getAula().getNombreAula();
        String diasClase = convertirDias(clase.getDias());
        String fechaInicio = convertirFecha(clase.getFechaInicio());
        String horaInicio = convertirHora(clase.getHoraInicio());
        String precio = String.valueOf(clase.getPrecio());

        EditarClaseDTO editarClaseDTO = new EditarClaseDTO(clase.getCodigo(),
                clase.getNombre(),
                nombreMaestro,
                clase.getModalidad(),
                aulaNombre,
                diasClase,
                fechaInicio,
                horaInicio,
                clase.getFechaFin(),
                clase.getHoraFin(),
                clase.getCapacidadAlumnos(),
                precio,
                clase.isActiva());
        return editarClaseDTO;
    }

    private String convertirDias(List<DayOfWeek> dias) {
        List<String> diasConvertidos = new ArrayList<>();

        for (DayOfWeek dia : dias) {
            switch (dia) {
                case MONDAY ->
                    diasConvertidos.add("Lun");
                case TUESDAY ->
                    diasConvertidos.add("Mar");
                case WEDNESDAY ->
                    diasConvertidos.add("Mie");
                case THURSDAY ->
                    diasConvertidos.add("Jue");
                case FRIDAY ->
                    diasConvertidos.add("Vie");
                case SATURDAY ->
                    diasConvertidos.add("Sab");
                case SUNDAY ->
                    diasConvertidos.add("Dom");
            }
        }

        return String.join(",", diasConvertidos);
    }

    public String convertirFecha(LocalDate fecha) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    public String convertirHora(LocalTime hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return hora.format(formatter);
    }

}
