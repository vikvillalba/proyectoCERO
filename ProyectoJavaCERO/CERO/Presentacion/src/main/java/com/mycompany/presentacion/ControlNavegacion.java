package com.mycompany.presentacion;


import ControlGestionarAlumnos.ControlGestionarAlumnos;
import ControlGestionarAlumnos.IControlGestionarAlumnos;
import ControlGestionarClases.ControlGestionarClases;
import ControlGestionarClases.IControlGestionarClases;
import DTOs.GestionarClases.AulaClaseDTO;
import DTOs.GestionarClases.ClaseListaDTO;
import DTOs.GestionarClases.EditarClaseDTO;
import DTOs.GestionarClases.MaestroDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Exception.GestionarAlumnosException;
import Exceptions.GestionarClasesException;
import FRM.GestionarAlumnos.FrmAdminAlumnos;
import FRM.GestionarAlumnos.FrmEditarAlumno;
import FRM.GestionarAlumnos.FrmInscripcionesClasesAlumno;
import FRM.GestionarAlumnos.FrmRegistrarNuevoAlumno;
import FRMs.*;
import FRMs.GestionarClases.FrmAdminClases;
import FRMs.GestionarClases.PanelScrollEditarClase;
import FRMs.GestionarClases.PanelScrollGuardarClase;
import FRMs.registroAsistencia.FrmAsistenciasClaseAnterior;
import FRMs.registroAsistencia.FrmAsistenciasClaseDiaActual;
import FRMs.registroAsistencia.FrmBuscarClase;
import FRMs.registroAsistencia.FrmBuscarClaseReporte;
import FRMs.registroAsistencia.FrmClasesExistentesAsistencia;
import FRMs.registroAsistencia.FrmDiasAnterioresClase;
import FRMs.registroAsistencia.FrmInscripcionesAlumno;
import FRMs.registroAsistencia.FrmJustificarFalta;
import FRMs.registroAsistencia.FrmRegistrarAsistenciaActualAlumno;
import FRMs.registroAsistencia.FrmReporteAsistencias;
import FRMs.registroAsistencia.FrmSeleccionarOpcion;
import com.mycompany.dtos.AlumnoBusquedaDTO;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.AsistenciaDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NombreClaseParam;
import com.mycompany.dtos.NuevaAsistenciaDTO;
import com.mycompany.dtos.NuevaInscripcionDTO;
import com.mycompany.dtos.NuevoPagoDTO;
import com.mycompany.dtos.PagoDTO;
import com.mycompany.dtos.PagoTarjetaDTO;
import com.mycompany.dtos.ReporteAsistenciaDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.inscribirclase.IInscribirClase;
import com.mycompany.inscribirclase.InscribirClase;
import com.mycompany.inscribirclase.excepciones.InscripcionException;
import com.mycompany.presentacion.excepciones.PresentacionException;
import com.mycompany.registroasistencias.IRegistroAsistencias;
import com.mycompany.registroasistencias.RegistroAsistencias;
import com.mycompany.registroasistencias.excepciones.AsistenciaException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author victoria
 */
public class ControlNavegacion {

    private static IInscribirClase inscribirClase = new InscribirClase();
    private static IRegistroAsistencias registroAsistencias = new RegistroAsistencias();
    private static IControlGestionarClases gestionarClases = new ControlGestionarClases();
    private static IControlGestionarAlumnos gestionarAlumnos = new ControlGestionarAlumnos();
    
    private static FrmMenuPrincipal menuPrincipal;

    // formularios CU inscribir 
    private static FrmPagoEfectivo pagoEfectivo;
    private static FrmPagoTarjeta pagoTarjeta;
    private static FrmRegistrarNuevoAlumnoInscribirClase frmRegistrarAlumnoInscribirClase;
    private static FrmInscribirClase inscribir;
    private static FrmAlumnosInscritos alumnosInscritos;
    private static FrmClasesExistentes clasesExistentes;
    private static FrmDatosClase datosClase;
    private static FrmFinalizarInscripcion finalizarInscripcion;

    //formularios CU_GESTIONAR CLASES
    private static FrmAdminClases frmAdminClases;
    private static PanelScrollGuardarClase frmRegistrarClase;
    private static PanelScrollEditarClase frmEditarClase;
    
    //formularios CU_Gestionar Alumnos
    private static FrmAdminAlumnos frmAdminAlumnos;
    private static FrmEditarAlumno frmEditarAlumno;
    private static FrmRegistrarNuevoAlumno frmRegistrarNuevoAlumno;
    private static FrmInscripcionesClasesAlumno frmInscripcionesClasesAlumno;

    private static JFrame frameActual;

    private ControlNavegacion() {
    }

    /**
     * Crear un Jframe y se asigana para tener un referencia al frame actual donde esta el usuario
     */
    public static JFrame crearFrame(JPanel panel) {
        if (frameActual != null) {
            frameActual.dispose(); // Cierra el frame anterior
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //(1230, 1300)
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null); // Centra la ventana
        frame.add(panel);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frameActual = frame; // Guarda referencia
        
        return frame;
    }

    /**
     * Muestra la pantalla de menú principal.
     */
    public static void mostrarMenuPrincipal() {
        menuPrincipal = new FrmMenuPrincipal();
        menuPrincipal.setVisible(true);
        frameActual = menuPrincipal;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Muestra la pantalla que inicia el caso de uso para inscribir a un alumno.
     */
    public static void mostrarInscribirClase() {
        frameActual.dispose();
        inscribir = new FrmInscribirClase();
        inscribir.setVisible(true);
        frameActual = inscribir;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Muestra la pantalla para registrar un pago en efectivo para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarPagoEfectivo(ClaseDTO clase, AlumnoDTO alumno) {
        pagoEfectivo = new FrmPagoEfectivo(clase, alumno);
        pagoEfectivo.setVisible(true);
        frameActual = pagoEfectivo;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Muestra la pantalla para registrar un pago con tarjeta para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarPagoTarjeta(ClaseDTO clase, AlumnoDTO alumno) {
        pagoTarjeta = new FrmPagoTarjeta(clase, alumno);
        pagoTarjeta.setVisible(true);
        frameActual = pagoTarjeta;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Muestra mensaje de pago exitoso para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarMensajePagoExitoso(JFrame parentComponent) {
        int response = JOptionPane.showConfirmDialog(parentComponent, "El pago se ha realizado exitosamente. La inscripción se ha registrado. :)",
                "Pago exitoso", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (response == JOptionPane.OK_OPTION) {
            // Si el usuario hace clic en OK, cierra la ventana raiz
            parentComponent.dispose();
            mostrarMenuPrincipal();
        }
    }

    /**
     * Muestra el JFrmae FrmFinalizarInscripcion.
     *
     * @param claseDTO el DTO que tendra los datos a mostrar.
     * @param alumno
     */
    public static void mostrarFrmFinalizarInscripcion(ClaseDTO claseDTO, AlumnoDTO alumno) {
        frameActual.dispose();
        finalizarInscripcion = new FrmFinalizarInscripcion(claseDTO, alumno);
        finalizarInscripcion.setVisible(true);
        frameActual = finalizarInscripcion;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public static void mostrarAlumnosInscritos(ClaseDTO clase) {
        frameActual.dispose();
        List<AlumnoDTO> alumnos = inscribirClase.obtenerAlumnosClase();
        alumnosInscritos = new FrmAlumnosInscritos(alumnos, clase);
        alumnosInscritos.setVisible(true);
        frameActual = alumnosInscritos;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void mostrarRegistrarAlumno(ClaseDTO clase) throws PresentacionException {
        frmRegistrarAlumnoInscribirClase = new FrmRegistrarNuevoAlumnoInscribirClase(clase);
        frmRegistrarAlumnoInscribirClase.setVisible(true);
        frameActual = frmRegistrarAlumnoInscribirClase;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void mostrarMensajeErrorConExcepcion(JFrame parentComponent, Exception exc) {
        JOptionPane.showMessageDialog(parentComponent, exc.getMessage(),
                "Error :(", JOptionPane.ERROR_MESSAGE);

    }

    // metodos que ven los formularios
    /**
     * Validación para el efectivo recibido al pagar una inscripción.
     */
    public static boolean validarEfectivoRecibido(BigDecimal costoClase, BigDecimal efectivo, JFrame frame) {
        return inscribirClase.validarEfectivoRecibido(costoClase, efectivo);

    }

    /**
     * Validación para el número de cuenta recibido al pagar una inscripción con tarjeta. Regresa la validación del SS del caso de uso
     */
    public static boolean validarNumeroCuenta(String numeroCuenta, JFrame frame) {
        return inscribirClase.validarNumeroCuenta(numeroCuenta);

    }

    /**
     * Validación para el propietario de cuenta recibido al pagar una inscripción con tarjeta. Regresa la validación del SS del caso de uso
     */
    public static boolean validarPropietarioTarjeta(String propietario, JFrame frame) {
        return inscribirClase.validarPropietarioTarjeta(propietario);

    }

    /**
     * Validación para la fecha de expiración de la tarjeta. Regresa la validación del SS del caso de uso
     */
    public static boolean validarFechaExpiracion(LocalDate fecha, JFrame frame) {
        return inscribirClase.validarFechaExpiracion(fecha);

    }

    /**
     * Validación del formato de CVV. Regresa la validación del SS del caso de uso
     */
    public static boolean validarCVV(int cvv, JFrame frame) {
        return inscribirClase.validarCVV(cvv);

    }

    /**
     * Llamada al SS del caso de uso para realizar una inscripción cuando el pago fue en efectivo.
     *
     * @param inscripcion la DTO con los datos validados
     * @return los datos de la inscripción ya que se guardaron
     */
    public static InscripcionDTO realizarInscripcionPagoEfectivo(NuevaInscripcionDTO inscripcion) {
        return inscribirClase.realizarInscripcionEfectivo(inscripcion);
    }

    /**
     * Llamada al SS del caso de uso para realizar una inscripción cuando el pago fue en línea.
     *
     * @param inscripcion la DTO con los datos validados
     * @return los datos de la inscripción ya que se guardaron
     */
    public static InscripcionDTO realizarInscripcionPagoTarjeta(NuevaInscripcionDTO inscripcion) {
        return inscribirClase.realizarInscripcionTarjeta(inscripcion);
    }

    /**
     * Llamada al SS del CU de inscripciones para realizar un pago con tarjeta. realiza la conexión con el SS de insfraestructura, que es el que maneja los datos de las tarjetas.
     */
    private static PagoRealizadoDTO confirmarPagoTarjeta(NuevoPagoTarjetaDTO pagoTarjeta) {
        // conexion con el sistema externo
        return inscribirClase.confirmarPagoTarjeta(pagoTarjeta);
    }

    /**
     * LLamada al SS del CU de inscripciones para guardar los datos ya validados del pago con tarjeta. el sistema solo guarda datos no sensibles de la cuenta bancaria.
     */
    public static void realizarPagoTarjeta(NuevoPagoTarjetaDTO pago, ClaseDTO clase, AlumnoDTO alumno, JFrame frame) {
        // validar datos 
        if (!validarNumeroCuenta(pago.getNumeroCuenta(), frame)) {
            try {
                throw new PresentacionException("El numero de cuenta es inválido. Verifique e intente nuevamente.");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
            }
        }
        if (!validarPropietarioTarjeta(pago.getPropietario(), frame)) {
            try {
                throw new PresentacionException("El propietario es inválido. Verifique e intente nuevamente.");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
            }
        }
        if (!validarFechaExpiracion(pago.getFechaExpiracion(), frame)) {
            try {
                throw new PresentacionException("La fecha de vencimiento es inválida. Verifique e intente nuevamente.");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
            }
        }
        if (!validarCVV(pago.getCvv(), frame)) {
            try {
                throw new PresentacionException("El CVV no puede estar vacío, ser menor a 3 dígitos o mayor a 4.  Verifique e intente nuevamente.");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
            }
        }

        // obtiene el pago de infraesttuctura
        PagoRealizadoDTO pagoRealizadoSistemaPagos = confirmarPagoTarjeta(pago);
        if (pagoRealizadoSistemaPagos == null) {
            mostrarMensajeErrorConExcepcion(frameActual, new PresentacionException("No se pudo registrar el pago en el sistema de pagos"));
            return;
        }
        // arma la dto
        PagoTarjetaDTO pagoTarjeta = new PagoTarjetaDTO(pagoRealizadoSistemaPagos.getCodigoConfirmacion(), pagoRealizadoSistemaPagos.getFechaHora());
        NuevoPagoDTO nuevoPagoDTO = new NuevoPagoDTO(pago.getMonto(), pagoTarjeta);
        
        PagoDTO pagoDTO = inscribirClase.realizarPagoTarjeta(nuevoPagoDTO);
        LocalDateTime fechaActual = LocalDateTime.now();
        NuevaInscripcionDTO inscripcionDTO = new NuevaInscripcionDTO(clase, alumno, fechaActual, pagoDTO);
        
        InscripcionDTO inscripcion = realizarInscripcionPagoTarjeta(inscripcionDTO);
        mostrarMensajePagoExitoso(frameActual);
        if (inscripcion == null) {
            try {
                throw new PresentacionException("Ocurrió un problema al realizar el pago y registrar la inscripción.");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
            }
        }
        
    }

    /**
     * LLamada al SS del CU de inscripciones para guardar los datos ya validados del pago en efectivo.
     */
    public static void realizarPagoEfectivo(NuevoPagoDTO nuevoPago, ClaseDTO clase, AlumnoDTO alumno) {

        PagoDTO pago = inscribirClase.realizarPagoEfectivo(nuevoPago);
        LocalDateTime fechaActual = LocalDateTime.now();
        NuevaInscripcionDTO inscripcionDTO = new NuevaInscripcionDTO(clase, alumno, fechaActual, pago);

        InscripcionDTO inscripcion = realizarInscripcionPagoEfectivo(inscripcionDTO);
        if (inscripcion == null) {
            try {
                throw new PresentacionException("Ocurrió un problema al realizar el pago y registrar la inscripción.");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frameActual, ex);
            }
        }
    }

    /**
     * Calcula el cambio para el efectivo recibido.
     */
    public static BigDecimal calcularCambio(BigDecimal precioClase, BigDecimal efectivoRecibido, JFrame frame) {
        try {
            if (!validarEfectivoRecibido(precioClase, efectivoRecibido, frame)) {
                try {
                    throw new PresentacionException("El efectivo ingresado no es suficiente para realizar el pago. Verifique e intente nuevamente.");
                } catch (PresentacionException ex) {
                    mostrarMensajeErrorConExcepcion(frame, ex);
                }
            }
            return inscribirClase.calcularCambio(precioClase, efectivoRecibido);
        } catch (InscripcionException ex) {
            mostrarMensajeErrorConExcepcion(frame, ex);   
        }
        return null;
    }

    /**
     * Muestra pantalla de DatosClase
     */
    public static void mostrarDatosClase(ClaseDTO claseDTO) {
        frameActual.dispose();
        datosClase = new FrmDatosClase(claseDTO);
        datosClase.setVisible(true);
        frameActual = datosClase;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    //MOSTRAR CLASES EXISTENTES
    private static boolean validarErrorNombreClase(JFrame frame, String nombre) {
        try {
            if (inscribirClase.validarNombreClaseVacio(nombre)) {
                mostrarMensajeErrorConExcepcion(frame, new PresentacionException(
                        "El campo está vacío, por favor ingrese el nombre de la clase a buscar"
                ));
                return true;
            }

            if (!inscribirClase.validarNombreClase(nombre)) {
                mostrarMensajeErrorConExcepcion(frame, new PresentacionException(
                        "El nombre de clase no existe"
                ));
                return true;
            }

            return false;
        } catch (InscripcionException ex) {
            mostrarMensajeErrorConExcepcion(frame, ex);
            return true;
        }
    }

    public static void mostrarClasesExistentes(String nombre) {
        if (validarErrorNombreClase(inscribir, nombre)) {            
            return;
        }

        NombreClaseParam nombreClase = new NombreClaseParam(nombre);
        List<ClaseDTO> clases = obtenerClases(nombreClase.getNombreClase());

        if (clases == null || clases.isEmpty()) {
            mostrarMensajeErrorClaseNoExiste();
            return;
        }

        frameActual.dispose();
        clasesExistentes = new FrmClasesExistentes(clases);
        clasesExistentes.setVisible(true);
        frameActual = clasesExistentes;
    }

    private static List<ClaseDTO> obtenerClases(String nombre) {
        try {
            return inscribirClase.buscarClasesPorNombre(nombre);
        } catch (InscripcionException ex) {
            mostrarMensajeErrorClaseNoExiste();
            return Collections.emptyList();
        }
    }

    //DATOS CLASE METODOS
    public static AlumnoDTO obtenerAlumno(Integer codigo) {
        AlumnoBusquedaDTO alumnoBusqueda = new AlumnoBusquedaDTO(codigo);
        return inscribirClase.obtenerAlumno(alumnoBusqueda);
    }

    //ValidarCampo
    public static Integer mostrarErrorcampoIdAlumno(String campo) {
        if (!campo.matches("\\d+")) {
            try {
                throw new PresentacionException("Ingrese solo números en el código de alumno.");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(datosClase, ex);
            }
        }
        Integer codigoAlumno = Integer.valueOf(campo);
        return codigoAlumno;
    }

    public static void mostrarFinalizarInscripcion(ClaseDTO clase, Integer codigoAlumno) {
        AlumnoBusquedaDTO alumnoBusqueda = new AlumnoBusquedaDTO(codigoAlumno);
        AlumnoDTO alumnoEncontrado = inscribirClase.obtenerAlumno(alumnoBusqueda);
        if (alumnoEncontrado == null) {
            try {
                throw new PresentacionException("El alumno no existe");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(datosClase, ex);
            }
            return;
        }
        try {
            //valida que el alumno no este inscrito ya en la clase
            if(inscribirClase.validarExistenciaInscripcion(clase, alumnoEncontrado)){
                mostrarFrmFinalizarInscripcion(clase, alumnoEncontrado);
            }
        } catch (InscripcionException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }
        
    }

    //agregarAlumno desde el flujo InscribirClase
    public static boolean AgregarAlumno(ClaseDTO clase, AlumnoDTO alumnoDTO) {
        try {
            gestionarAlumnos.validarDatosCompletosAlumno(alumnoDTO);
            //llamar a gestionar 
            AlumnoDTO alumnoRegistrado = inscribirClase.agregarAlumno(alumnoDTO);
            if (alumnoRegistrado == null) {
                return false;
            } else {
                JOptionPane.showMessageDialog(null,
                        "El alumno se registró exitosamente. El código de integrante es: " + alumnoRegistrado.getCodigo().toString(),
                        "Alumno Registrado :)", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null,
                        "El alumno : " + alumnoRegistrado.getCodigo().toString() + " \"se inscribira en la clase :  " + clase.getNombre(),
                        "proxima inscripcion ", JOptionPane.INFORMATION_MESSAGE);
                mostrarFrmFinalizarInscripcion(clase, alumnoRegistrado);
                return true;
            }
        } catch (GestionarAlumnosException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }
        return false;
    }

    // formularios para el CU de registro de asistencias
    /**
     * Muestra el formulario que contiene las opciones de registro de asistencia.
     */
    public static void mostrarSeleccionarOpcionAsistencia() {
        FrmSeleccionarOpcion seleccionOpcionAsistencia = new FrmSeleccionarOpcion();
        seleccionOpcionAsistencia.setVisible(true);
        frameActual = seleccionOpcionAsistencia;
    }

    /**
     * Muestra el formulario que permite iniciar el registro una asistencia individual.
     */
    public static void mostrarRegistrarAsistenciaAlumno() {
        FrmRegistrarAsistenciaActualAlumno asistenciaActual = new FrmRegistrarAsistenciaActualAlumno();
        asistenciaActual.setVisible(true);
        frameActual = asistenciaActual;
    }

    /**
     * Muestra las inscripciones del alumno (dependiendo del día del sistema). permite seleccionar una y registrar la asistencia del alumno.
     *
     * @param codigo codigo del alumno que el usuario ingresó en la pantalla anterior.
     */
    public static void mostrarInscripcionesAlumno(String codigo, JFrame frame) {

        Integer codigoAlumno = mostrarErrorcampoIdAlumno(codigo);
        AlumnoBusquedaDTO alumnoBusqueda = new AlumnoBusquedaDTO(codigoAlumno);
        AlumnoDTO alumno = registroAsistencias.obtenerAlumno(alumnoBusqueda);

        if (alumno == null) {
            mostrarMensajeErrorAlumnoNoExiste();
        }

        try {
            List<InscripcionDTO> inscripcionesAlumno = registroAsistencias.obtenerInscripcionesAlumno(alumno);
            FrmInscripcionesAlumno inscripciones = new FrmInscripcionesAlumno(inscripcionesAlumno, alumno);
            inscripciones.setVisible(true);

        } catch (AsistenciaException ex) {
            mostrarMensajeErrorConExcepcion(frame, ex);
            mostrarRegistrarAsistenciaAlumno();
        }

    }

    public static void mostrarMensajeErrorAlumnoNoExiste() {
        JOptionPane.showMessageDialog(null, "El id ingresado no corresponde a ningún alumno existente.",
                "Error :(", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Muestra el formulario que permite buscar una clase por nombre.
     */
    public static void mostrarBuscarClase() {
        FrmBuscarClase buscarClase = new FrmBuscarClase();
        buscarClase.setVisible(true);
        frameActual = buscarClase;
    }

    /**
     * Muestra las clases resultantes de la búsqueda para registro de asistencias.
     *
     * @param nombre nombre de la clase que el usuario ingresó
     */
    public static void mostrarClasesExistentes(String nombre, JFrame frame) {
        if (validarErrorNombreClaseAsistencia(frame, nombre)) {
            mostrarBuscarClase();
            return;
        }

        NombreClaseParam nombreClase = new NombreClaseParam(nombre);
        List<ClaseDTO> clases = obtenerClasesAsistencia(nombreClase.getNombreClase());

        if (clases == null || clases.isEmpty()) {
            mostrarMensajeErrorClaseNoExiste();
            return;
        }

        FrmClasesExistentesAsistencia clasesResultado = new FrmClasesExistentesAsistencia(clases);
        clasesResultado.setVisible(true);
        frameActual = clasesResultado;

    }

    /**
     * Obtiene las clases existentes en el sistema para el registro de asistencias.
     */
    private static List<ClaseDTO> obtenerClasesAsistencia(String nombre) {
        try {
            return registroAsistencias.obtenerClasesNombre(nombre);
        } catch (AsistenciaException ex) {
            mostrarMensajeErrorClaseNoExiste();
            return Collections.emptyList();

        }
    }

    /**
     * Mensaje que se muestra cuando no existen clases que coincidan con el parámetro de búsqueda.
     */
    public static void mostrarMensajeErrorClaseNoExiste() {
        JOptionPane.showMessageDialog(null, "El nombre ingresado no corresponde a ninguna clase existente.",
                "Error :(", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Valida que el nombre de clase ingresado por el usuario sea correcto para buscarlo en los registros.
     */
    private static boolean validarErrorNombreClaseAsistencia(JFrame frame, String nombre) {
        try {
            if (registroAsistencias.validarNombreClaseVacio(nombre)) {
                mostrarMensajeErrorConExcepcion(frame, new PresentacionException(
                        "El campo está vacío, por favor ingrese el nombre de la clase a buscar"
                ));
                return true;
            }

            if (!registroAsistencias.validarNombreClase(nombre)) {
                mostrarMensajeErrorConExcepcion(frame, new PresentacionException(
                        "El nombre de clase no existe"
                ));
                return true;
            }

            return false;
        } catch (AsistenciaException ex) {
            mostrarMensajeErrorConExcepcion(frame, ex);
            return true;
        }
    }

    /**
     * Muestra la pantalla para seleccionar una clase a la que se le generarán reportes.
     */
    public static void mostrarBuscarClaseReporte() {
        try {
            FrmBuscarClaseReporte seleccionClaseReporte = new FrmBuscarClaseReporte(registroAsistencias.obtenerClasesActivas());
            seleccionClaseReporte.setVisible(true);
            frameActual = seleccionClaseReporte;
        } catch (AsistenciaException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }
    }

    /**
     * Muestra la pantalla para generar reportes de asistencias de una clase en específico.
     *
     * @param clase clase que el usuario seleccionó
     */
    public static void mostrarReportesAsistencias(ClaseListaDTO clase) {
        FrmReporteAsistencias reporteAsistencias = new FrmReporteAsistencias(clase);
        reporteAsistencias.setVisible(true);
        frameActual = reporteAsistencias;
    }

    /**
     * Registra la asistencia de un alumno en una clase con la fecha actual del sistema.
     *
     * @param nuevaAsistencia Los datos empaquetados de la asistencia que se quiere registrar.
     */
    public static void registrarAsistenciaAlumno(NuevaAsistenciaDTO nuevaAsistencia, JFrame frame) {
        AsistenciaDTO asistenciaAlumno = registroAsistencias.obtenerAsistenciaAlumnoClase(nuevaAsistencia.getAlumno(), nuevaAsistencia.getClase());

        if (asistenciaAlumno != null) {
            mostrarMensajeErrorAlumnoAsistenciaYaRegistrada(nuevaAsistencia.getAlumno(), nuevaAsistencia.getClase());
            return;
        }

        try {
            registroAsistencias.registrarAsistenciaIndividual(nuevaAsistencia);
            mostrarMensajeAsistenciaAgregadaCorrectamente(nuevaAsistencia.getAlumno(), nuevaAsistencia.getClase());
        } catch (AsistenciaException ex) {
            mostrarMensajeErrorConExcepcion(frame, ex);
        }
    }

    /**
     * Mensaje de confirmación que se muestra cuando una asistencia se registró correctamente.
     */
    public static void mostrarMensajeAsistenciaAgregadaCorrectamente(AlumnoDTO alumno, ClaseDTO clase) {
        JOptionPane.showMessageDialog(null, "Asistencia registrada para el alumno: " + alumno.getNombreCompleto() + " para la clase: " + clase.getNombre(),
                "Asistencia registrada :)", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Mensaje de error que se muestra cuando el alumno ya tiene una asistencia registrada para una clase en el día actual.
     */
    public static void mostrarMensajeErrorAlumnoAsistenciaYaRegistrada(AlumnoDTO alumno, ClaseDTO clase) {
        JOptionPane.showMessageDialog(null, "El alumno: " + alumno.getNombreCompleto() + " ya tiene la asistencia correspondiente registrada para la clase: " + clase.getNombre(),
                "Error :(", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Muestra la pantalla con las fechas que se ha impartido una clase hasta la fecha actual
     *
     * @param clase sobre la que se está trabajando.
     */
    public static void mostrarDiasAnterioresClase(ClaseDTO clase) {
        List<LocalDate> dias = registroAsistencias.obtenerDiasClase(clase);
        FrmDiasAnterioresClase diasClase = new FrmDiasAnterioresClase(dias, clase);
        diasClase.setVisible(true);
        frameActual = diasClase;
    }

    /**
     * Muestra la pantalla con las asistencias anteriores de los alumnos inscritos en una clase.
     *
     * @param clase sobre la que se está trabajando.
     * @param diaClase día en el que se impartió la clase.
     */
    public static void mostrarAsistenciasAnterioresClase(ClaseDTO clase, LocalDate diaClase) {
        try {

            List<AsistenciaDTO> asistenciasClase = registroAsistencias.obtenerAsistenciasClase(clase, diaClase);
            FrmAsistenciasClaseAnterior pantallaAsistencias = new FrmAsistenciasClaseAnterior(diaClase, clase, asistenciasClase);
            pantallaAsistencias.setVisible(true);
            frameActual = pantallaAsistencias;
        } catch (AsistenciaException ex) {
            mostrarMensajeErrorConExcepcion(null, ex);
        }
    }

    public static void mostrarJustificarFalta(AsistenciaDTO asistencia) {
        FrmJustificarFalta justificarFalta = new FrmJustificarFalta(asistencia);
        justificarFalta.setVisible(true);
        frameActual = justificarFalta;
    }

    public static void justificarFalta(AsistenciaDTO faltaJustificada) {
        try {
            AsistenciaDTO faltaValidada = registroAsistencias.justificarFalta(faltaJustificada);
            mostrarMensajeFaltaJustificadaCorrectamente(faltaValidada.getAlumno(), faltaValidada.getClase());
        } catch (AsistenciaException ex) {
            mostrarMensajeErrorLimiteFaltasJustificadas(ex.getMessage());
        }

    }

    public static void mostrarMensajeErrorLimiteFaltasJustificadas(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje,
                "Error :(", JOptionPane.ERROR_MESSAGE);

    }

    public static void mostrarMensajeFaltaJustificadaCorrectamente(AlumnoDTO alumno, ClaseDTO clase) {
        JOptionPane.showMessageDialog(null, "Falta justificada para el alumno: " + alumno.getNombreCompleto() + " para la clase: " + clase.getNombre(),
                "Falta justificada :)", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarAsistenciasClaseDiaActual(ClaseDTO clase, LocalDate dia) {
        List<LocalDate> diasClase = registroAsistencias.obtenerDiasClase(clase);
        if (!diasClase.contains(dia)) {
            mostrarMensajeErrorClaseDiaNoValido(clase);
            return;
        }
        try {
            List<AsistenciaDTO> asistenciasActuales = registroAsistencias.obtenerAsistenciasClase(clase, dia);
            List<InscripcionDTO> inscripciones = registroAsistencias.obtenerInscripcionesClase(clase);
            FrmAsistenciasClaseDiaActual asistenciasActual = new FrmAsistenciasClaseDiaActual(clase, dia, asistenciasActuales, inscripciones);
            asistenciasActual.setVisible(true);
            frameActual = asistenciasActual;
        } catch (AsistenciaException ex) {
            mostrarMensajeErrorConExcepcion(null, ex);
        }
    }

    public static void mostrarMensajeErrorClaseDiaNoValido(ClaseDTO clase) {
        JOptionPane.showMessageDialog(null, "No se pueden registrar asistencias para la clase: " + clase.getNombre() + " porque hoy no hay sesión programada.",
                "Sin asistencias para registrar", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void actualizarAsistencias(List<AsistenciaDTO> asistencias, ClaseDTO clase) {
        try {
            registroAsistencias.actualizarAsistencias(asistencias);
            mostrarAsistenciasAlumnosClaseRegistradasCorrectamente(clase);

        } catch (AsistenciaException ex) {
            mostrarMensajeErrorConExcepcion(null, ex);
        }
    }

    public static void mostrarAsistenciasAlumnosClaseRegistradasCorrectamente(ClaseDTO clase) {
        JOptionPane.showMessageDialog(null, "Se han registrado las asistencias de hoy para la clase: " + clase.getNombre(),
                "Asistencias registradas :)", JOptionPane.INFORMATION_MESSAGE);
    }

    //METODOS CU_GESTIONAR CLASES 
    public static void mostrarFrmAdminClases() {
        try {
            frameActual.dispose();
            List<ClaseListaDTO> clases = gestionarClases.buscarClasesExistentes();
            List<ClaseListaDTO> clasesInactivas = gestionarClases.buscarClasesInactivas();
            List<ClaseListaDTO> clasesActivas = gestionarClases.buscarClasesActivas();
            frmAdminClases = new FrmAdminClases(clases, clasesInactivas, clasesActivas);
            frmAdminClases.setVisible(true);
            frameActual = frmAdminClases;
            frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } catch (GestionarClasesException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }

    }

    public static void mostrarFrmRegistrarClase() {
        frameActual.dispose();
        List<MaestroDTO> maestros = gestionarClases.obtenerListaMaestros();
        List<AulaClaseDTO> aulas = gestionarClases.obtenerListasAulas();
        frmRegistrarClase = new PanelScrollGuardarClase(maestros, aulas);
        // agregar panel a un JFrame
        crearFrame(frmRegistrarClase);
    }

    public static void mostrarFrmEditarClase(ClaseListaDTO clase) {
        frameActual.dispose();
        EditarClaseDTO claseEditarDTO = gestionarClases.obtenerClaseLista(clase);
        frmEditarClase = new PanelScrollEditarClase(claseEditarDTO);
        crearFrame(frmEditarClase);
    }

    public static void registrarNuevaClase(NuevaClaseDTO nuevaClase) {
        try {
            gestionarClases.registrarNuevaClase(nuevaClase);
            JOptionPane.showMessageDialog(frameActual, "Clase registrada exitosamente");
            mostrarFrmAdminClases();
        } catch (GestionarClasesException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }
    }

    public static void editarClase(EditarClaseDTO editClase) {
        try {
            gestionarClases.editarClase(editClase);
            JOptionPane.showMessageDialog(frameActual, "Datos de la clase actualizados exitosamente");
        } catch (GestionarClasesException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }

    }

    public static void eliminarClase(ClaseListaDTO claseLista) {
        gestionarClases.eliminarClase(claseLista);
        JOptionPane.showMessageDialog(frameActual, "Clase eliminada exitosamente");
    }

    public static List<ClaseListaDTO> buscarClasesNombre(String nombreClase) {
        try {
            return gestionarClases.buscarClaseListaNombre(nombreClase);
        } catch (GestionarClasesException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }
        return null;
    }

    public static List<ReporteAsistenciaDTO> obtenerReportesAsistencia(
            Integer codigoClase, Integer codigoAlumno, LocalDate fechaInicio, LocalDate fechaFin) {
        try {
            return registroAsistencias.obtenerReporteAsistencias(codigoClase, codigoAlumno, fechaInicio, fechaFin);
        } catch (AsistenciaException ex) {
            mostrarMensajeErrorConExcepcion(null, ex);
            return new ArrayList<>();
        }
    }
    
    //CU GESTIONAR ALUMNOS
    public static void mostrarFrmAdminAlumnos(){
        frameActual.dispose();
        List<AlumnoDTO> alumnos = gestionarAlumnos.obtenerAlumnosDTOLista();
        frmAdminAlumnos = new FrmAdminAlumnos(alumnos);
        frmAdminAlumnos.setVisible(true);
        frameActual = frmAdminAlumnos;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public static void mostrarFrmRegistrarNuevoAlumno(){
        frameActual.dispose();
        frmRegistrarNuevoAlumno = new FrmRegistrarNuevoAlumno();
        frmRegistrarNuevoAlumno.setVisible(true);
        frameActual = frmRegistrarNuevoAlumno;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    }
    public static void mostrarFrmEditarAlumno(AlumnoDTO alumno){
        frameActual.dispose();
        frmEditarAlumno = new FrmEditarAlumno(alumno);
        frmEditarAlumno.setVisible(true);
        frameActual = frmEditarAlumno;
        frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
   
    public static void registrarNuevoAlumno(AlumnoDTO nuevoAlumno) {
        try {
            gestionarAlumnos.registrarNuevoAlumno(nuevoAlumno);
            JOptionPane.showMessageDialog(frameActual, "Alumn@ registrad@ exitosamente");
            mostrarFrmAdminAlumnos();
        } catch (GestionarAlumnosException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }
        
    }
    
    public static void editarAlumno(AlumnoDTO alumno) {

        try {
            gestionarAlumnos.editarAlumno(alumno);
            JOptionPane.showMessageDialog(frameActual, "Alumn@ editad@ exitosamente");
            mostrarFrmAdminAlumnos();
        } catch (GestionarAlumnosException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }

    }
    public static void eliminarAlumno(AlumnoDTO alumno){
        gestionarAlumnos.eliminarAlumno(alumno);
    }

    public static void mostrarFrmInscripcionesClasesAlumno(AlumnoDTO alumno) {
       
        List<InscripcionClaseDTO> inscripciones = obtenerInscripciones(alumno);
        if(!inscripciones.isEmpty()){
            frameActual.dispose();
            frmInscripcionesClasesAlumno = new FrmInscripcionesClasesAlumno(inscripciones);
            frmInscripcionesClasesAlumno.setVisible(true);
            frameActual = frmInscripcionesClasesAlumno;
            frameActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else{
            try {
                throw new PresentacionException("El alumno no tiene inscripciones");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frameActual, ex);
            }
        }

    }

    public static List<InscripcionClaseDTO> obtenerInscripciones(AlumnoDTO alumnoDTO) {

        try {
            return gestionarAlumnos.obtenerInscripciones(alumnoDTO);
        } catch (GestionarAlumnosException ex) {
            mostrarMensajeErrorConExcepcion(frameActual, ex);
        }
        return null;
    }

    public static void cancelarInscripcion(InscripcionClaseDTO inscripcion) {
        gestionarAlumnos.cancelarInscripcion(inscripcion);
        JOptionPane.showMessageDialog(frameActual, "La inscripcion ya esta inactiva");
    }
}
