/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import Entidades.Alumno;
import com.mycompany.dtos.AlumnoDTO;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Jack Murrieta
 */
public class AlumnoMapper implements IAlumnoMapper{

    public AlumnoMapper() {
    }

    @Override
    public AlumnoDTO convertirAlumnoDTO(Alumno alumno) {
        int edad = calcularEdad(alumno.getFechaNacimiento());
        String id = alumno.getIdString();
        return new AlumnoDTO(
                id,
                alumno.getCodigo(),alumno.getFechaNacimiento(), // código único
                edad, // edad calculada
                alumno.getApellidoPaterno(),
                alumno.getApellidoMaterno(),
                alumno.getNombre(),
                alumno.getTelefono(),
                alumno.getCorreoElectronico()
        );
    }

    private int calcularEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return 0; // o lanzar una excepción si prefieres forzar fecha válida
        }

        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

}
