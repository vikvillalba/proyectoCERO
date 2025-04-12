/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.AlumnoEntidad;
import InterfazDAOs.IAlumnoDAO;
import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class AlumnoDAO implements IAlumnoDAO {

    @Override
    public AlumnoEntidad obtenerAlumno(Long idAlumno) {
        AlumnoEntidad alumnoMock = new AlumnoEntidad(
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
    public AlumnoEntidad registrarAlumnoNuevo(AlumnoEntidad alumno) {
          AlumnoEntidad alumnoMock = new AlumnoEntidad(
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
