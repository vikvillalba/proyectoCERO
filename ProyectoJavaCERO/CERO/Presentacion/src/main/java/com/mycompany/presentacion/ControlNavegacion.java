package com.mycompany.presentacion;

import FRMs.*;
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
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NombreClaseParam;
import com.mycompany.dtos.NuevaAsistenciaDTO;
import com.mycompany.dtos.NuevaInscripcionDTO;
import com.mycompany.dtos.NuevoPagoDTO;
import com.mycompany.dtos.PagoDTO;
import com.mycompany.dtos.PagoTarjetaDTO;
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
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author victoria
 */
public class ControlNavegacion {

    private static IInscribirClase inscribirClase = new InscribirClase();
    private static IRegistroAsistencias registroAsistencias = new RegistroAsistencias();

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
            mostrarInscribirClase();
            return;
        }

        NombreClaseParam nombreClase = new NombreClaseParam(nombre);
        List<ClaseDTO> clases = obtenerClases(nombreClase.getNombreClase());

        if (clases == null || clases.isEmpty()) {
            mostrarMensajeErrorClaseNoExiste();
            return;
        }

        clasesExistentes = new FrmClasesExistentes(clases);
        clasesExistentes.setVisible(true);
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
     * Muestra el formulario que permite iniciar el registro una asistencia individual.
     */
    public static void mostrarRegistrarAsistenciaAlumno() {
        FrmRegistrarAsistenciaActualAlumno asistenciaActual = new FrmRegistrarAsistenciaActualAlumno();
        asistenciaActual.setVisible(true);
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
    }

    /**
     * Muestra las clases resultantes de la búsqueda para registro de asistencias.
     *
     * @param nombre nombre de la clase que el usuario ingresó
     */
    public static void mostrarClasesExistentes(String nombre, JFrame frame) {
        if (validarErrorNombreClaseAsistencia(frame, nombre)) {
            mostrarInscribirClase();
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
    }

    /**
     * Muestra la pantalla con las asistencias anteriores de los alumnos inscritos en una clase.
     *
     * @param clase sobre la que se está trabajando.
     * @param diaClase día en el que se impartió la clase.
     */
    public static void mostrarAsistenciasAnterioresClase(ClaseDTO clase, LocalDate diaClase) {
        try {
            // llamar registroAsistencias y obtener las asistencias de esa clase en ese dia (llamar a la dao?? idk)
            List<AsistenciaDTO> asistenciasClase = registroAsistencias.obtenerAsistenciasClase(clase, diaClase);
            FrmAsistenciasClaseAnterior pantallaAsistencias = new FrmAsistenciasClaseAnterior(diaClase, clase, asistenciasClase);
            pantallaAsistencias.setVisible(true);
        } catch (AsistenciaException ex) {
            mostrarMensajeErrorConExcepcion(null, ex);
        }
    }

    public static void mostrarJustificarFalta(AsistenciaDTO asistencia) {
        FrmJustificarFalta justificarFalta = new FrmJustificarFalta(asistencia);
        justificarFalta.setVisible(true);
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

}
