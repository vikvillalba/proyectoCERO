/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.AlumnoEntidad;
import Entidades.ClaseEntidad;
import Entidades.InscripcionEntidad;
import Entidades.MetodoPagoTarjeta;
import Entidades.PagoEntidad;
import InterfazDAOs.IinscripcionDAO;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class InscripcionDAO implements IinscripcionDAO{

    @Override
    public List<InscripcionEntidad> obtenerInscripcionesClase(Long idClase) {
        // Mocks de las entidades necesarias
        ClaseEntidad claseMock = new ClaseEntidad(
                1L,
                "Contemporaneo Avanzado",
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 30),
                "Cesar Gomez",
                new BigDecimal("2500.00")
        );
        AlumnoEntidad alumnoMock = new AlumnoEntidad(
                1L,
                "Torres",
                "Murrieta",
                "Jack Tadeo",
                "5551234567",
                LocalDate.of(2002, 8, 15),
                "jackmurrieta@gmail.com"
        );
        MetodoPagoTarjeta metodoPagoTarjetaMock = new MetodoPagoTarjeta(
                2L,
                "CONFIRM123456", 
                LocalDateTime.now() 
        );
        PagoEntidad pagoMock = new PagoEntidad(1L, new BigDecimal("500.00"), LocalDate.now(), true, metodoPagoTarjetaMock); 

        // Crear una inscripción mock
        InscripcionEntidad inscripcionMock = new InscripcionEntidad(
                1L,
                claseMock,
                alumnoMock,
                LocalDate.now(),
                pagoMock
        );

        // Retornar lista con un solo mock
        return List.of(inscripcionMock);
    }

    @Override
    public InscripcionEntidad generarInscripcion(Long idAlumno, Long idClase) {
        ClaseEntidad claseMock = new ClaseEntidad(
                idClase,
                "Contemporaneo Avanzado",
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 30),
                "Cesar Gomez",
                new BigDecimal("2500.00")
        );
        AlumnoEntidad alumnoMock = new AlumnoEntidad(
                idAlumno,
                "Torres",
                "Murrieta",
                "Jack Tadeo",
                "5551234567",
                LocalDate.of(2002, 8, 15),
                "jackmurrieta@gmail.com"
        );
        PagoEntidad pagoMock = new PagoEntidad(1L, new BigDecimal("500.00"), LocalDate.now(), true, null);
        
                InscripcionEntidad inscripcionMock = new InscripcionEntidad(
                1L,
                claseMock,
                alumnoMock,
                LocalDate.now(),
                pagoMock
        );
        return inscripcionMock;
    }
    
}
