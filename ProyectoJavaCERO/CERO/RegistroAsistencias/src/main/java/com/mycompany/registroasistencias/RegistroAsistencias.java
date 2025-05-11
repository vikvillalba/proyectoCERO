package com.mycompany.registroasistencias;

import com.mycompany.dtos.AlumnoBusquedaDTO;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.AsistenciaDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NuevaAsistenciaDTO;
import com.mycompany.negocio.Fabricas.FabricaObjetosNegocio;
import com.mycompany.negocio.InterfazBO.IAlumnosBO;
import com.mycompany.negocio.InterfazBO.IAsistenciasBO;
import com.mycompany.negocio.InterfazBO.IInscripcionesBO;
import com.mycompany.negocio.excepciones.NegocioException;
import com.mycompany.registroasistencias.excepciones.AsistenciaException;
import java.util.List;

/**
 * clase que implementa los métodos de la interfaz IRegistroAsistencias
 * @author victoria
 */
public class RegistroAsistencias implements IRegistroAsistencias{

    private IInscripcionesBO inscripcionesBO;
    private IAlumnosBO alumnosBO;
    private IAsistenciasBO asistenciasBO;

    public RegistroAsistencias() {
        this.inscripcionesBO = FabricaObjetosNegocio.obtenerInscripcionesBO();
        this.alumnosBO = FabricaObjetosNegocio.obtenerAlumnosBO();
        this.asistenciasBO = FabricaObjetosNegocio.obtenerAsistenciasBO();
    }
    
    
    
    @Override
    public List<InscripcionDTO> obtenerInscripcionesAlumno(AlumnoDTO alumno) throws AsistenciaException{
        try {
            return this.inscripcionesBO.obtenerInscripcionesAlumno(alumno);
        } catch (NegocioException ex) {
            throw new AsistenciaException(ex.getMessage());
        }
    }

    @Override
    public AlumnoDTO obtenerAlumno(AlumnoBusquedaDTO alumnoBusqueda) {
        List<AlumnoDTO> alumnos = alumnosBO.obtenerAlumnos();

        for (AlumnoDTO alumno : alumnos) {
            if (alumno.getCodigo() == alumnoBusqueda.getCodigo()) {
                return alumno;
            }
        }
        return null;
    }

    @Override
    public AsistenciaDTO registrarAsistenciaIndividual(NuevaAsistenciaDTO nuevaAsistencia) throws AsistenciaException {
        try {
            return this.asistenciasBO.registrarAsistencia(nuevaAsistencia);
        } catch (NegocioException ex) {
            throw new AsistenciaException(ex.getMessage());
        }
    }

    @Override
    public AsistenciaDTO obtenerAsistenciasAlumnoClase(AlumnoDTO alumno, ClaseDTO clase) {
        return this.asistenciasBO.obtenerAsistenciaAlumnoClase(alumno, clase);
    }

    
}
