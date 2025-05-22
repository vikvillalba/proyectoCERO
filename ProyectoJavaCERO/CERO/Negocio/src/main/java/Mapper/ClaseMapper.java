/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTOs.GestionarClases.AulaClaseDTO;
import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.MaestroDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Maestro;
import com.mycompany.dtos.ClaseDTO;
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

//    Actualizar los cupos de las clases dependiend de las inscripciones
//    uso del Observer
    @Override
    public Clase convertirClaseEntidad(NuevaClaseDTO nuevaClase, Maestro maestro, AulaClase aula) {
        double precioDouble = nuevaClase.getPrecio();
        BigDecimal precio = BigDecimal.valueOf(precioDouble);
        
        return new Clase(
                nuevaClase.getNombreClase(),
                maestro.getIdString(),
                aula.getIdString(),
                nuevaClase.getModalidad(),
                nuevaClase.getDiasClase(),
                nuevaClase.getHoraInicio(),
                nuevaClase.getHoraFin(),
                nuevaClase.getFechaInicio(),
                nuevaClase.getFechaFin(),
                nuevaClase.getCapacidadAlumnos(),
                new BigDecimal(nuevaClase.getPrecio()),
                nuevaClase.isActiva()
        );
    }
    
    @Override
    public ClaseListaDTO convertirClaseListaDTO(Clase clase, Maestro maestro, AulaClase aula) {
        
        List<DayOfWeek> dias = clase.getDias();
        String diasTexto = convertirDias(dias);
        
        String fechaInicio = convertirFecha(clase.getFechaInicio());
        String fechaFin = convertirFecha(clase.getFechaFin());
        String periodo = fechaInicio + " - " + fechaFin;
        
        String horaInicio = convertirHora(clase.getHoraInicio());
        String horaFin = convertirHora(clase.getHoraFin());
        String horario = diasTexto + "\n" + horaInicio + "-" + horaFin;
        
        return new ClaseListaDTO(
                clase.getCodigo(),
                clase.getNombre(),
                horario,
                maestro != null ? maestro.getNombreCompleto() : "Sin asignar",
                clase.getCapacidadAlumnos(),clase.getCuposDisponibles(), //cupos disponibles
                periodo,
                aula != null ? aula.getNombreAula() : "Sin aula",
                clase.isActiva(),
                clase.getPrecio()
        );
    }
    
    @Override
    public EditarClaseDTO convertirEditarClase(Clase clase, Maestro maestro, AulaClase aula) {
        String nombreMaestro = maestro != null ? maestro.getNombreCompleto() : "Sin asignar";
        String nombreAula = aula != null ? aula.getNombreAula() : "Sin aula";
        
        String diasTexto = convertirDias(clase.getDias());
        String fechaInicio = convertirFecha(clase.getFechaInicio());
        String horaInicio = convertirHora(clase.getHoraInicio());
        String precio = String.valueOf(clase.getPrecio());
        MaestroDTO maestroDTO = new MaestroDTO(maestro.getIdString(), nombreMaestro);
        AulaClaseDTO aulaDTO = new AulaClaseDTO(aula.getIdString(), nombreAula);
        
        return new EditarClaseDTO(clase.getCodigo(),
                clase.getNombre(),
                maestroDTO,
                clase.getModalidad(),
                aulaDTO,
                clase.getDias(),
                clase.getHoraInicio(),
                clase.getHoraFin(),
                clase.getFechaInicio(),
                clase.getFechaFin(),
                clase.getCapacidadAlumnos(), precio,
                clase.isActiva(), clase.getCuposDisponibles(), 
                fechaInicio, horaInicio,diasTexto);
    }
    
    //Mapper claseDTO de inscribir clase 
    public ClaseDTO converitirClaseDTO(Clase clase){
        return new ClaseDTO(clase.getCodigo(),
                clase.getNombre(),
                clase.getDias(),
                clase.getHoraInicio(),
                clase.getHoraFin(),
                clase.getNombreMaestro(),
                clase.getPrecio(),
                clase.getFechaInicio(),
                clase.getFechaFin());
    
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
    
    private String convertirFecha(LocalDate fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }
    
    private String convertirHora(LocalTime hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return hora.format(formatter);
    }
}
