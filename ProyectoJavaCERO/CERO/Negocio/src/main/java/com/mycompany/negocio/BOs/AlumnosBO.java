
package com.mycompany.negocio.BOs;

import DAOs.IAlumnosDAO;
import DAOs.IClasesDAO;
import DAOs.IInscripcionesDAO;
import Entidades.Alumno;
import Entidades.AulaClase;
import Entidades.Clase;
import Entidades.Inscripcion;
import Entidades.Maestro;
import Excepciones.PersistenciaException;
import GestionarClasesPersistencia.AulasClaseDAO;
import GestionarClasesPersistencia.IAulasClaseDAO;
import GestionarClasesPersistencia.IMaestrosDAO;
import GestionarClasesPersistencia.MaestrosDAO;
import Mapper.IInscripcionMapper;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.InscripcionClaseDTO;
import com.mycompany.negocio.InterfazBO.IAlumnosBO;
import com.mycompany.negocio.excepciones.NegocioException;
import implementaciones.ClasesDAO;
import implementaciones.InscripcionesDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victoria
 */
public class AlumnosBO implements IAlumnosBO {

    private IAlumnosDAO alumnosDAO;
    private IInscripcionesDAO inscripcionesDAO;
    private IClasesDAO clasesDAO;
    private IAulasClaseDAO aulaDAO;
    private IMaestrosDAO maestro;
    private IInscripcionMapper inscripcionMapper;

    public AlumnosBO(IAlumnosDAO alumnosDAO) {
        this.alumnosDAO = alumnosDAO;
        this.inscripcionesDAO = new InscripcionesDAO();
        this.clasesDAO = new ClasesDAO();
        this.aulaDAO = new AulasClaseDAO();
        this.maestro = new MaestrosDAO();
    }

    @Override
    public List<AlumnoDTO> obtenerAlumnos() {
        List<Alumno> alumnosObtenidos = alumnosDAO.obtenerAlumnos();
        List<AlumnoDTO> alumnos = new ArrayList<>();

        for (Alumno alumno : alumnosObtenidos) {
            AlumnoDTO alumnoDTO = new AlumnoDTO(
                    alumno.getCodigo(),
                    alumno.getApellidoPaterno(),
                    alumno.getApellidoMaterno(),
                    alumno.getNombre(),
                    alumno.getTelefono(),
                    alumno.getFechaNacimiento(),
                    alumno.getCorreoElectronico()
            );

            alumnos.add(alumnoDTO);
        }

        return alumnos;
    }

    @Override
    public AlumnoDTO agregarAlumno(AlumnoDTO alumnoDTO) {
        Alumno alumno = new Alumno(
                alumnoDTO.getApellidoPaterno(),
                alumnoDTO.getApellidoMaterno(),
                alumnoDTO.getNombre(),
                alumnoDTO.getTelefono(),
                alumnoDTO.getFechaNacimiento(),
                alumnoDTO.getCorreoElectronico()
        );

        Alumno alumnoRegistrado = alumnosDAO.registrarAlumnoNuevo(alumno);

        AlumnoDTO alumnoRegistradoDTO = new AlumnoDTO(
                alumnoRegistrado.getCodigo(),
                alumnoRegistrado.getApellidoPaterno(),
                alumnoRegistrado.getApellidoMaterno(),
                alumnoRegistrado.getNombre(),
                alumnoRegistrado.getTelefono(),
                alumnoRegistrado.getFechaNacimiento(),
                alumnoRegistrado.getCorreoElectronico()
        );

        return alumnoRegistradoDTO;
    }

    @Override
    public void eliminarAlumno(AlumnoDTO alumnoDTO) {
        alumnosDAO.eliminarAlumno(alumnoDTO.getCodigo());

    }

    @Override
    public void editarAlumno(AlumnoDTO alumnoDTO) {
        Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(alumnoDTO.getCodigo());
        alumno.setApellidoMaterno(alumnoDTO.getApellidoMaterno());
        alumno.setApellidoPaterno(alumnoDTO.getApellidoPaterno());
        alumno.setCorreoElectronico(alumnoDTO.getCorreoElectronico());
        alumno.setFechaNacimiento(alumnoDTO.getFechaNacimiento());
        alumno.setTelefono(alumnoDTO.getTelefono());
        alumno.setNombre(alumnoDTO.getNombre());

        alumnosDAO.editarAlumno(alumno);
    }

    @Override
    public List<InscripcionClaseDTO> obtenerInscripciones(AlumnoDTO alumnoDTO) throws NegocioException {
        // mandar clase y alumno
        //buscarAlumno
        List<InscripcionClaseDTO> inscripcionesDTO = new ArrayList<>();
        Alumno alumno = alumnosDAO.obtenerAlumnoPorCodigo(alumnoDTO.getCodigo());
        List<Inscripcion> inscripcionesAlumno = inscripcionesDAO.obtenerInscripcionesAlumno(alumno);

        for (Inscripcion inscripcion : inscripcionesAlumno) {
            try {
                Clase clase = clasesDAO.buscarClaseObjectID(inscripcion.getClase());
                AulaClase aula = aulaDAO.buscarClase(clase.getIdAulaString());
                Maestro maestroEncontrado = maestro.buscarMaestro(clase.getIdMaestroString());

                InscripcionClaseDTO inscripcionDTO = inscripcionMapper.convertirInscripcionDTO(alumno,
                        clase,
                        maestroEncontrado,
                        aula);

                inscripcionesDTO.add(inscripcionDTO);
            } catch (PersistenciaException ex) {
                throw new NegocioException(ex.getMessage());
            }
        }
        return inscripcionesDTO;

    }

}

