package ControlGestionarAlumnos;

import Exception.GestionarAlumnosException;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.InscripcionClaseDTO;
import com.mycompany.negocio.Fabricas.FabricaObjetosNegocio;
import com.mycompany.negocio.InterfazBO.IAlumnosBO;
import com.mycompany.negocio.InterfazBO.IInscripcionesBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public class ControlGestionarAlumnos implements IControlGestionarAlumnos {

    private final IAlumnosBO alumnosBO;
    private final IInscripcionesBO inscripcionesBO;

    public ControlGestionarAlumnos() {
        this.alumnosBO = FabricaObjetosNegocio.obtenerAlumnosBO();
        this.inscripcionesBO = FabricaObjetosNegocio.obtenerInscripcionesBO();
    }

    @Override
    public AlumnoDTO registrarNuevoAlumno(AlumnoDTO nuevoAlumno) throws GestionarAlumnosException {
        validarDatosCompletosAlumno(nuevoAlumno);
        return alumnosBO.agregarAlumno(nuevoAlumno);

    }

    @Override
    public void eliminarAlumno(AlumnoDTO alumnoDTO) {
        alumnosBO.eliminarAlumno(alumnoDTO);

    }

    @Override
    public void editarAlumno(AlumnoDTO alumnoDTO) throws GestionarAlumnosException {
        validarDatosCompletosAlumno(alumnoDTO);
        alumnosBO.editarAlumno(alumnoDTO);

    }

    @Override
    public List<InscripcionClaseDTO> obtenerInscripciones(AlumnoDTO alumnoDTO) throws GestionarAlumnosException {
        try {
            return alumnosBO.obtenerInscripciones(alumnoDTO);
        } catch (NegocioException ex) {
            throw new GestionarAlumnosException(ex.getMessage());
        }
    }

    @Override
    public List<AlumnoDTO> obtenerAlumnosDTOLista() {
        return alumnosBO.obtenerAlumnosDTOLista();
    }

    //dar de baja de un inscripcion
    @Override
    public void cancelarInscripcion(InscripcionClaseDTO inscripcion) {
        inscripcionesBO.cancelarInscripcion(inscripcion);
    }

    private void validarDatosCompletosAlumno(AlumnoDTO alumno) throws GestionarAlumnosException {
        validarApellidoMaterno(alumno.getApellidoMaterno());
        validarApellidoPaterno(alumno.getApellidoPaterno());
        validarCorreoElectronicoAlumno(alumno.getCorreoElectronico());
        validarNombreAlumno(alumno.getNombre());
        validarFechaNacimientoAlumno(alumno.getFechaNacimiento());
        validarTelefonoAlumno(alumno.getTelefono());
    }

    private void validarCorreoElectronicoAlumno(String correo) throws GestionarAlumnosException {
        if (correo == null || !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new GestionarAlumnosException("El correo electrónico no es válido.");
        }
    }

    private void validarFechaNacimientoAlumno(LocalDate fechaNacimiento) throws GestionarAlumnosException {
        if (fechaNacimiento == null) {
            throw new GestionarAlumnosException("La fecha de nacimiento no puede ser nula.");
        }
    }

    private void validarTelefonoAlumno(String telefono) throws GestionarAlumnosException {
        if (telefono == null || !telefono.matches("^[0-9]{10}$")) {
            throw new GestionarAlumnosException("El número de teléfono debe contener exactamente 10 dígitos.");
        }
    }

    private void validarNombreAlumno(String nombre) throws GestionarAlumnosException {
        if (nombre == null || !nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñüÜ\\s]+$")) {
            throw new GestionarAlumnosException("El nombre solo debe contener letras y espacios.");
        }
    }

    private void validarApellidoMaterno(String apellidoMaterno) throws GestionarAlumnosException {
        if (apellidoMaterno == null || !apellidoMaterno.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñüÜ]+$")) {
            throw new GestionarAlumnosException("El apellido materno solo debe contener letras.");
        }
    }

    private void validarApellidoPaterno(String apellidoPaterno) throws GestionarAlumnosException {
        if (apellidoPaterno == null || !apellidoPaterno.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñüÜ]+$")) {
            throw new GestionarAlumnosException("El apellido paterno solo debe contener letras.");
        }
    }

}
