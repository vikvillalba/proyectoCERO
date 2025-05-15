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
import com.mycompany.negocio.InterfazBO.IClasesBO;
import com.mycompany.negocio.InterfazBO.IInscripcionesBO;
import com.mycompany.negocio.excepciones.NegocioException;
import com.mycompany.registroasistencias.excepciones.AsistenciaException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * clase que implementa los métodos de la interfaz IRegistroAsistencias
 *
 * @author victoria
 */
public class RegistroAsistencias implements IRegistroAsistencias {

    private IInscripcionesBO inscripcionesBO;
    private IAlumnosBO alumnosBO;
    private IAsistenciasBO asistenciasBO;
    private IClasesBO clasesBO;

    public RegistroAsistencias() {
        this.inscripcionesBO = FabricaObjetosNegocio.obtenerInscripcionesBO();
        this.alumnosBO = FabricaObjetosNegocio.obtenerAlumnosBO();
        this.asistenciasBO = FabricaObjetosNegocio.obtenerAsistenciasBO();
        this.clasesBO = FabricaObjetosNegocio.obtenerClasesBO();
    }

    @Override
    public List<InscripcionDTO> obtenerInscripcionesAlumno(AlumnoDTO alumno) throws AsistenciaException {
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
    public AsistenciaDTO obtenerAsistenciaAlumnoClase(AlumnoDTO alumno, ClaseDTO clase) {
        return this.asistenciasBO.obtenerAsistenciaAlumnoClase(alumno, clase);
    }

    @Override
    public boolean validarNombreClase(String nombre) throws AsistenciaException {
        try {
            List<ClaseDTO> clasesExistentes = clasesBO.obtenerClases();

            // Separar el nombre ingresado en palabras
            String[] palabras = nombre.trim().split("\\s+");

            for (String palabra : palabras) {
                for (ClaseDTO claseExistente : clasesExistentes) {
                    // Comparación LIKE "%palabra%", ignorando mayúsculas y minúsculas
                    if (claseExistente.getNombre().toLowerCase().contains(palabra.toLowerCase())) {
                        return true;
                    }
                }
            }

            return false;
        } catch (NegocioException ex) {
            throw new AsistenciaException(ex.getMessage());
        }
    }

    @Override
    public boolean validarNombreClaseVacio(String nombre) {
        if (nombre == null || nombre.trim().isEmpty() || nombre.equalsIgnoreCase("ingresa nombre clase...")) {
            return true;
        }
        return false;
    }

    @Override
    public List<ClaseDTO> obtenerClasesNombre(String nombre) throws AsistenciaException {
        try {
            return this.clasesBO.obtenerClasesNombre(nombre);
        } catch (NegocioException ex) {
            throw new AsistenciaException(ex.getMessage());
        }
    }

    @Override
    public List<LocalDate> obtenerDiasClase(ClaseDTO clase) {
        List<LocalDate> fechasClase = new ArrayList<>();

        LocalDate fechaInicio = clase.getFechaInicio();
        LocalDate fechaFin = LocalDate.now();

        while (!fechaInicio.isAfter(fechaFin)) {
            if (clase.getDias().contains(fechaInicio.getDayOfWeek())) {
                fechasClase.add(fechaInicio);
            }
            fechaInicio = fechaInicio.plusDays(1);
        }

        Collections.reverse(fechasClase);
        return fechasClase;
    }

    @Override
    public List<AsistenciaDTO> obtenerAsistenciasClase(ClaseDTO clase, LocalDate diaClase) throws AsistenciaException {
        try {
            return this.asistenciasBO.obtenerAsistenciasClase(clase, diaClase);
        } catch (NegocioException ex) {
            throw new AsistenciaException(ex.getMessage());
        }
    }

    @Override
    public AsistenciaDTO justificarFalta(AsistenciaDTO faltaJustificada) throws AsistenciaException {
        validarFaltasJustificadasAlumno(faltaJustificada);
        return this.asistenciasBO.justificarFalta(faltaJustificada);

    }

    @Override
    public AsistenciaDTO validarFaltasJustificadasAlumno(AsistenciaDTO asistencia) throws AsistenciaException {
        List<AsistenciaDTO> faltasJustificadas = this.asistenciasBO.obtenerFaltasJustificadas(asistencia);
        Integer limiteFaltas = this.clasesBO.obtenerLimiteFaltas(asistencia.getClase());
        if (faltasJustificadas.size() >= limiteFaltas) {
            throw new AsistenciaException("El alumno " + asistencia.getAlumno().getNombreCompleto() + " ya alcanzó el límite de " + limiteFaltas + " faltas justificadas.");
        }
        return asistencia;
    }

}
