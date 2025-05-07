package implementaciones;

import Entidades.Alumno;
import java.time.LocalDate;
import DAOs.IAlumnosDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class AlumnosDAO implements IAlumnosDAO {

    private List<Alumno> alumnos;
    private Integer codigoAlumno = 1;

    public AlumnosDAO() {
        alumnos = new ArrayList<>();
         Alumno alumno1 = new Alumno(
                1,
                "Torres",
                "Murrieta",
                "Jack Tadeo",
                "5551234567",
                LocalDate.of(2002, 8, 15),
                "jackmurrieta@gmail.com"
        );
        alumnos.add(alumno1);
    }

    @Override
    public Alumno obtenerAlumno(Integer idAlumno) {
       
        for (Alumno alumno : alumnos) {
            if(alumno.getCodigo().equals(idAlumno)){
                return alumno;
            }
        }
        return null;
    }

    @Override
    public Alumno registrarAlumnoNuevo(Alumno alumno) {
        alumno.setCodigo(codigoAlumno);
        codigoAlumno++;
        alumnos.add(alumno);
        return alumno;
    }

    @Override
    public List<Alumno> obtenerAlumnos() {
        return alumnos;
    }

}
