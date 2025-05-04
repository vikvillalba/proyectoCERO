/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.Alumno;
import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class AlumnosDAO implements IAlumnosDAO {

    @Override
    public Alumno obtenerAlumno(Long idAlumno) {
        Alumno alumnoMock = new Alumno(
                1L,
                "Torres",
                "Murrieta",
                "Jack Tadeo",
                "5551234567",
                LocalDate.of(2002, 8, 15),
                "jackmurrieta@gmail.com"
        );
        return alumnoMock;
    }

    @Override
    public Alumno registrarAlumnoNuevo(Alumno alumno) {
          Alumno alumnoMock = new Alumno(
            null,
            "Lopez",
            "Torres",
            "Maria",
            "5551234567",
            LocalDate.of(2002, 8, 15),
            "maria.fernanda@gmail.com"
        );
          return alumnoMock;
    }


}
