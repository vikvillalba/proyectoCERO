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
    private Integer codigoAlumno = 4;

    public AlumnosDAO() {
        alumnos = new ArrayList<>();
         Alumno alumno1 = new Alumno( 1,"Torres","Murrieta","Jack Tadeo", "5551234567",LocalDate.of(2002, 8, 15),"jackmurrieta@gmail.com");
         Alumno alumno2 = new Alumno( 2,"Soto","Medina","Paula", "6554323456",LocalDate.of(2004, 11, 16),"example@gmail.com");
         Alumno alumno3 = new Alumno( 3,"Ruiz","Pacheco","Marisol", "6445678754",LocalDate.of(2005, 05, 20),"soool@gmail.com");
        alumnos.add(alumno1);
        alumnos.add(alumno2);
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
