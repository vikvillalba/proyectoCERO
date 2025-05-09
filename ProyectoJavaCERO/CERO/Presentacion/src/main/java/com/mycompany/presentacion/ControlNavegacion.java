package com.mycompany.presentacion;

import FRMs.*;
import FRMs.registroAsistencia.FrmBuscarClase;
import FRMs.registroAsistencia.FrmBuscarClaseReporte;
import FRMs.registroAsistencia.FrmClasesExistentesAsistencia;
import FRMs.registroAsistencia.FrmRegistrarAsistenciaActualAlumno;
import FRMs.registroAsistencia.FrmReporteAsistencias;
import FRMs.registroAsistencia.FrmSeleccionarOpcion;
import com.mycompany.dtos.AlumnoBusquedaDTO;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NombreClaseParam;
import com.mycompany.dtos.NuevaInscripcionDTO;
import com.mycompany.dtos.NuevoPagoDTO;
import com.mycompany.dtos.PagoDTO;
import com.mycompany.dtos.PagoTarjetaDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.inscribirclase.IInscribirClase;
import com.mycompany.inscribirclase.implementaciones.InscribirClase;
import com.mycompany.presentacion.excepciones.PresentacionException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author victoria
 */
public class ControlNavegacion {

    private static ControlNavegacion controlNavegacion;
    private static IInscribirClase inscribirClase;

    private static FrmMenuPrincipal menuPrincipal;

    // formularios CU inscribir 
    private static FrmPagoEfectivo pagoEfectivo;
    private static FrmPagoTarjeta pagoTarjeta;
    private static FrmRegistrarAlumno registrarAlumno;
    private static FrmInscribirClase inscribir;
    private static FrmAlumnosInscritos alumnosInscritos;
    private static FrmClasesExistentes clasesExistentes;
    private static FrmDatosClase datosClase;
    private static FrmFinalizarInscripcion finalizarInscripcion;

    private ControlNavegacion() {
        inscribirClase = new InscribirClase();
    }

    public static ControlNavegacion obtenerControlNavegacion() {
        if (controlNavegacion == null) {
            controlNavegacion = new ControlNavegacion();
        }
        return controlNavegacion;
    }

    /**
     * Muestra la pantalla de menú principal.
     */
    public static void mostrarMenuPrincipal() {
        menuPrincipal = new FrmMenuPrincipal();
        menuPrincipal.setVisible(true);
    }

    /**
     * Muestra la pantalla que inicia el caso de uso para inscribir a un alumno.
     */
    public static void mostrarInscribirClase() {
        inscribir = new FrmInscribirClase();
        inscribir.setVisible(true);
    }

    /**
     * Muestra la pantalla para registrar un pago en efectivo para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarPagoEfectivo(ClaseDTO clase, AlumnoDTO alumno) {
        pagoEfectivo = new FrmPagoEfectivo(clase, alumno);
        pagoEfectivo.setVisible(true);
    }

    /**
     * Muestra la pantalla para registrar un pago con tarjeta para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarPagoTarjeta(ClaseDTO clase, AlumnoDTO alumno) {
        pagoTarjeta = new FrmPagoTarjeta(clase, alumno);
        pagoTarjeta.setVisible(true);
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
     */
    public static void mostrarFrmFinalizarInscripcion(ClaseDTO claseDTO, AlumnoDTO alumno) {
        finalizarInscripcion = new FrmFinalizarInscripcion(claseDTO, alumno);
        finalizarInscripcion.setVisible(true);
    }

    public static void mostrarAlumnosInscritos(ClaseDTO clase) {
        List<AlumnoDTO> alumnos = inscribirClase.obtenerAlumnosClase();
        alumnosInscritos = new FrmAlumnosInscritos(alumnos, clase);
        alumnosInscritos.setVisible(true);
    }

    public static void mostrarRegistrarAlumno(ClaseDTO clase) throws PresentacionException {
        registrarAlumno = new FrmRegistrarAlumno(clase);
        registrarAlumno.setVisible(true);
    }

    public static void mostrarMensajeErrorConExcepcion(JFrame parentComponent, PresentacionException exc) {
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
        // arma la dto
        PagoTarjetaDTO pagoTarjeta = new PagoTarjetaDTO(pagoRealizadoSistemaPagos.getCodigoConfirmacion(), pagoRealizadoSistemaPagos.getFechaHora());
        NuevoPagoDTO nuevoPagoDTO = new NuevoPagoDTO(pago.getMonto(), pagoTarjeta);

        PagoDTO pagoDTO = inscribirClase.realizarPagoTarjeta(nuevoPagoDTO);
        LocalDateTime fechaActual = LocalDateTime.now();
        NuevaInscripcionDTO inscripcionDTO = new NuevaInscripcionDTO(clase, alumno, fechaActual, pagoDTO);

        InscripcionDTO inscripcion = realizarInscripcionPagoTarjeta(inscripcionDTO);
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
    public static void realizarPagoEfectivo(NuevoPagoDTO nuevoPago, ClaseDTO clase, AlumnoDTO alumno, JFrame frame) {

        PagoDTO pago = inscribirClase.realizarPagoEfectivo(nuevoPago);
        LocalDateTime fechaActual = LocalDateTime.now();
        NuevaInscripcionDTO inscripcionDTO = new NuevaInscripcionDTO(clase, alumno, fechaActual, pago);

        InscripcionDTO inscripcion = realizarInscripcionPagoEfectivo(inscripcionDTO);
        if (inscripcion == null) {
            try {
                throw new PresentacionException("Ocurrió un problema al realizar el pago y registrar la inscripción.");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
            }
        }
    }

    /**
     * Calcula el cambio para el efectivo recibido.
     */
    public static BigDecimal calcularCambio(BigDecimal precioClase, BigDecimal efectivoRecibido, JFrame frame) {
        if (!validarEfectivoRecibido(precioClase, efectivoRecibido, frame)) {
            try {
                throw new PresentacionException("El efectivo ingresado no es suficiente para realizar el pago. Verifique e intente nuevamente.");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
            }
        }
        return inscribirClase.calcularCambio(precioClase, efectivoRecibido);
    }

    /**
     * Muestra pantalla de DatosClase
     */
    public static void mostrarDatosClase(ClaseDTO claseDTO) {
        datosClase = new FrmDatosClase(claseDTO);
        datosClase.setVisible(true);
        clasesExistentes.dispose();
    }

    //MOSTRAR CLASES EXISTENTES
    private static boolean validarErrorNombreClase(JFrame frame, String nombre) {
        if (inscribirClase.validarNombreClaseVacio(nombre) == true) {
            try {
                throw new PresentacionException("El campo esta vacio, porfavor ingrese el nombre de la clase a buscar");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
                return true;
            }
        }
        if (inscribirClase.validarNombreClase(nombre) == false) {
            try {
                throw new PresentacionException("El nombre de clase no existe");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
                return true;
            }
        }
        return false;
    }

    public static void mostrarClasesExistente(String nombre) {

        if (validarErrorNombreClase(inscribir, nombre) == true) {
            mostrarInscribirClase();
            return;
        }

        NombreClaseParam nombreClase = new NombreClaseParam(nombre);
        List<ClaseDTO> clases = obtenerClases(nombreClase.getNombreClase());
        clasesExistentes = new FrmClasesExistentes(clases);
        clasesExistentes.setVisible(true);
    }

    private static List<ClaseDTO> obtenerClases(String nombre) {
        return inscribirClase.buscarClasesPorNombre(nombre);
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
            return null;
        }
        Integer codigoAlumno = Integer.valueOf(campo);
        return codigoAlumno;
    }

    public static void mostrarFinalizarInscripcion(ClaseDTO clase, String campo) {

        Integer codigoAlumno = mostrarErrorcampoIdAlumno(campo);
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
        mostrarFrmFinalizarInscripcion(clase, alumnoEncontrado);
    }

    public static boolean validarDatosAlumno(AlumnoDTO alumnoDTO) {
        if (inscribirClase.validarApellidoPaterno(alumnoDTO.getApellidoPaterno())) {
            return true;
        }
        if (inscribirClase.validarApellidoMaterno(alumnoDTO.getApellidoMaterno())) {
            return true;
        }
        if (inscribirClase.validarNombreAlumno(alumnoDTO.getNombre())) {
            return true;
        }
        if (inscribirClase.validarFechaNacimientoAlumno(alumnoDTO.getFechaNacimiento())) {
            return true;
        }
        if (inscribirClase.validarCorreoElectronicoAlumno(alumnoDTO.getCorreoElectronico())) {
            return true;
        }
        if (inscribirClase.validarTelefonoAlumno(alumnoDTO.getTelefono())) {
            return true;
        }
        return false;
    }

    public static boolean AgregarAlumno(AlumnoDTO alumnoDTO) {
        AlumnoDTO alumnoRegistrado = inscribirClase.agregarAlumno(alumnoDTO);
        if (alumnoRegistrado == null) {
            return false;
        } else {
            return true;
        }
    }

    // formularios para el CU de registro de asistencias
    /**
     * Muestra el formulario que contiene las opciones de registro de asistencia.
     */
    public static void mostrarSeleccionarOpcionAsistencia() {
        FrmSeleccionarOpcion seleccionOpcionAsistencia = new FrmSeleccionarOpcion();
        seleccionOpcionAsistencia.setVisible(true);
    }

    /**
     * Muestra el formulario que permite registrar una asistencia individual.
     */
    public static void mostrarRegistrarAsistenciaAlumno() {
        FrmRegistrarAsistenciaActualAlumno asistenciaActual = new FrmRegistrarAsistenciaActualAlumno();
        asistenciaActual.setVisible(true);
    }

    /**
     * Muestra el formulario que permite buscar una clase por nombre.
     */
    public static void mostrarBuscarClase() {
        FrmBuscarClase buscarClase = new FrmBuscarClase();
        buscarClase.setVisible(true);
    }

    /**
     * Muestra las clases resultantes de la búsqueda.
     *
     * @param nombre nombre de la clase que el usuario ingresó
     */
    public static void mostrarClasesExistentes(String nombre) {
        if (validarErrorNombreClase(inscribir, nombre) == true) {
            mostrarInscribirClase();
            return;
        }

        NombreClaseParam nombreClase = new NombreClaseParam(nombre);
        List<ClaseDTO> clases = obtenerClases(nombreClase.getNombreClase());
        FrmClasesExistentesAsistencia clasesResultado = new FrmClasesExistentesAsistencia(clases);
        clasesResultado.setVisible(true);
    }

    /**
     * Muestra la pantalla para seleccionar una clase a la que se le generarán reportes.
     */
    public static void mostrarBuscarClaseReporte() {
        FrmBuscarClaseReporte seleccionClaseReporte = new FrmBuscarClaseReporte();
        seleccionClaseReporte.setVisible(true);
    }

    /**
     * Muestra la pantalla para generar reportes de asistencias de una clase en específico.
     *
     * @param clase clase que el usuario seleccionó
     */
    public static void mostrarReportesAsistencias() {
        FrmReporteAsistencias reporteAsistencias = new FrmReporteAsistencias();
        reporteAsistencias.setVisible(true);
    }

}
