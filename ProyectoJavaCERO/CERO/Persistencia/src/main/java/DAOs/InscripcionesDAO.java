/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.Alumno;
import Entidades.Clase;
import Entidades.Inscripcion;
import Entidades.MetodoPagoTarjeta;
import Entidades.Pago;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import InterfazDAOs.IInscripcionesDAO;

/**
 *
 * @author Usuario
 */
public class InscripcionesDAO implements IInscripcionesDAO{

    @Override
    public List<Inscripcion> obtenerInscripcionesClase(Long idClase) {
        // Mocks de las entidades necesarias
        Clase claseMock = new Clase(
                1L,
                "Contemporaneo Avanzado",
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 30),
                "Cesar Gomez",
                new BigDecimal("2500.00")
        );
        Alumno alumnoMock = new Alumno(
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
        Pago pagoMock = new Pago(1L, new BigDecimal("500.00"), LocalDate.now(), true, metodoPagoTarjetaMock); 

        // Crear una inscripción mock
        Inscripcion inscripcionMock = new Inscripcion(
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
    public Inscripcion generarInscripcion(Long idAlumno, Long idClase) {
        Clase claseMock = new Clase(
                idClase,
                "Contemporaneo Avanzado",
                Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 30),
                "Cesar Gomez",
                new BigDecimal("2500.00")
        );
        Alumno alumnoMock = new Alumno(
                idAlumno,
                "Torres",
                "Murrieta",
                "Jack Tadeo",
                "5551234567",
                LocalDate.of(2002, 8, 15),
                "jackmurrieta@gmail.com"
        );
        Pago pagoMock = new Pago(1L, new BigDecimal("500.00"), LocalDate.now(), true, null);
        
                Inscripcion inscripcionMock = new Inscripcion(
                1L,
                claseMock,
                alumnoMock,
                LocalDate.now(),
                pagoMock
        );
        return inscripcionMock;
    }
    
}
