package com.mycompany.presentacion;

import FRMs.*;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.inscribirclase.IInscribirClase;
import com.mycompany.inscribirclase.implementaciones.InscribirClase;
import com.mycompany.negocio.dtos.AlumnoBusquedaDTO;
import com.mycompany.negocio.dtos.AlumnoDTO;
import com.mycompany.negocio.dtos.ClaseDTO;
import com.mycompany.negocio.dtos.InscripcionDTO;
import com.mycompany.negocio.dtos.NombreClaseParam;
import com.mycompany.negocio.dtos.NuevaInscripcionDTO;
import com.mycompany.negocio.dtos.NuevoPagoDTO;
import com.mycompany.negocio.dtos.PagoDTO;
import com.mycompany.negocio.dtos.PagoTarjetaDTO;
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
    
    // formularios
    private static FrmAlumnosInscritos alumnosInscritos;
    private static FrmClasesExistentes clasesExistentes;
    private static FrmDatosClase datosClase;
    private static FrmFinalizarInscripcion finalizarInscripcion;
    private static FrmMenuPrincipal menuPrincipal;
    private static FrmPagoEfectivo pagoEfectivo;
    private static FrmPagoTarjeta pagoTarjeta;
    private static FrmRegistrarAlumno registrarAlumno;
    private static FrmInscribirClase inscribir;

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
        int response = JOptionPane.showConfirmDialog(parentComponent, "El pago se ha realizado exitosamente :)",
                "Pago exitoso", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (response == JOptionPane.OK_OPTION) {
            // Si el usuario hace clic en OK, cierra la ventana raiz
            parentComponent.dispose();
            mostrarMenuPrincipal();
        }
    }
    
    /**
     * Muestra el JFrmae FrmFinalizarInscripcion.
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
    
    public static void mostrarRegistrarAlumno(ClaseDTO clase) throws PresentacionException{
        registrarAlumno = new FrmRegistrarAlumno(clase);
        registrarAlumno.setVisible(true);
    }

    public static void mostrarMensajeErrorConExcepcion(JFrame parentComponent, PresentacionException exc) {
        JOptionPane.showMessageDialog(parentComponent, exc.getMessage(),
                "Error :(",JOptionPane.ERROR_MESSAGE);

    }
 
    // metodos que ven los formularios
    public static boolean validarEfectivoRecibido(BigDecimal costoClase, BigDecimal efectivo, JFrame parentComponent) {
       return inscribirClase.validarEfectivoRecibido(costoClase, efectivo);

    }

    public static boolean validarNumeroCuenta(String numeroCuenta, JFrame parentComponent) {
        return inscribirClase.validarNumeroCuenta(numeroCuenta);

    }

    public static boolean validarPropietarioTarjeta(String propietario, JFrame parentComponent) {
        return inscribirClase.validarPropietarioTarjeta(propietario);

    }

    public static boolean validarFechaExpiracion(LocalDate fecha, JFrame parentComponent) {
        return inscribirClase.validarFechaExpiracion(fecha);

    }

    public static boolean validarCVV(int cvv, JFrame parentComponent) {
        return inscribirClase.validarCVV(cvv);

    }

    public static InscripcionDTO realizarInscripcion(NuevaInscripcionDTO inscripcion) {
        return inscribirClase.realizarInscripcion(inscripcion);
    }

    private static PagoRealizadoDTO confirmarPagoTarjeta(NuevoPagoTarjetaDTO pagoTarjeta) {
        // conexion con el sistema externo
        return inscribirClase.confirmarPagoTarjeta(pagoTarjeta);
    }

    public static void realizarPagoTarjeta(NuevoPagoTarjetaDTO pago, ClaseDTO clase, AlumnoDTO alumno, JFrame parentComponent) throws PresentacionException {
        // validar datos 
        if(!validarNumeroCuenta(pago.getNumeroCuenta(), parentComponent)){
            throw new PresentacionException("El numero de cuenta es inválido. Verifique e intente nuevamente.");
        }
        if(!validarPropietarioTarjeta(pago.getPropietario(), parentComponent)) {
            throw new PresentacionException("El propietario es inválido. Verifique e intente nuevamente.");
        }
        if(!validarFechaExpiracion(pago.getFechaExpiracion(), parentComponent)){
            throw new PresentacionException("La fecha de vencimiento es inválida. Verifique e intente nuevamente.");
        }
        if(!validarCVV(pago.getCvv(), parentComponent)){
            throw new PresentacionException("El CVV no puede estar vacío, ser menor a 3 dígitos o mayor a 4.  Verifique e intente nuevamente.");
        }

        PagoRealizadoDTO pagoRealizadoSistemaPagos = confirmarPagoTarjeta(pago);
        PagoTarjetaDTO pagoTarjeta = new PagoTarjetaDTO(pagoRealizadoSistemaPagos.getCodigoConfirmacion(), pagoRealizadoSistemaPagos.getFechaHora());
        NuevoPagoDTO nuevoPagoDTO = new NuevoPagoDTO(pago.getMonto(), pagoTarjeta);

        PagoDTO pagoDTO = inscribirClase.realizarPagoTarjeta(nuevoPagoDTO);
        LocalDateTime fechaActual = LocalDateTime.now();
        NuevaInscripcionDTO inscripcionDTO = new NuevaInscripcionDTO(clase, alumno, fechaActual, pagoDTO);

        InscripcionDTO inscripcion = realizarInscripcion(inscripcionDTO);
        if (inscripcion == null) {
            throw new PresentacionException("Ocurrió un problema al realizar el pago y registrar la inscripción.");
        }

    }

    public static void realizarPagoEfectivo(NuevoPagoDTO nuevoPago, ClaseDTO clase, AlumnoDTO alumno, JFrame parentComponent) throws PresentacionException {

        PagoDTO pago = inscribirClase.realizarPagoEfectivo(nuevoPago);
        LocalDateTime fechaActual = LocalDateTime.now();
        NuevaInscripcionDTO inscripcionDTO = new NuevaInscripcionDTO(clase, alumno, fechaActual, pago);

        InscripcionDTO inscripcion = realizarInscripcion(inscripcionDTO);
        if (inscripcion == null) {
            throw new PresentacionException("Ocurrió un problema al realizar el pago y registrar la inscripción.");
        }
    }

    public static BigDecimal calcularCambio(BigDecimal precioClase, BigDecimal efectivoRecibido, JFrame parentComponent) throws PresentacionException {
        if (!validarEfectivoRecibido(precioClase, efectivoRecibido, parentComponent)) {
            throw new PresentacionException("El efectivo ingresado no es suficiente para realizar el pago. Verifique e intente nuevamente.");
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
   
    private static boolean validarErrorNombreClase(JFrame frame,String nombre){
        if(inscribirClase.validarNombreClaseVacio(nombre)== true){
            try {
                throw new PresentacionException("El campo esta vacio, porfavor ingrese el nombre de la clase a buscar");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
                frame.dispose();
                return true;
            }
        }
        if(inscribirClase.validarNombreClase(nombre) == false){
            try { 
                throw new PresentacionException("El nombre de clase no existe");
            } catch (PresentacionException ex) {
                mostrarMensajeErrorConExcepcion(frame, ex);
               frame.dispose();
               return true;
            }
        }
        frame.dispose();
        return false;
    }
        
    public static void mostrarClasesExistente(String nombre){
        
        if(validarErrorNombreClase(inscribir, nombre)== true){
            return;
        }
        
        NombreClaseParam nombreClase = new NombreClaseParam(nombre);
        List<ClaseDTO> clases = obtenerClaseLista(nombreClase.getNombreClase());
        clasesExistentes = new FrmClasesExistentes(clases);
        clasesExistentes.setVisible(true);
    }
    
    private static List<ClaseDTO> obtenerClaseLista(String nombre){
        return inscribirClase.buscarClasesPorNombre(nombre);
    }
    
    
    //DATOS CLASE METODOS
    public static AlumnoDTO obtenerAlumno(Integer codigo){
        AlumnoBusquedaDTO alumnoBusqueda = new AlumnoBusquedaDTO(codigo);
        return inscribirClase.obtenerAlumno(alumnoBusqueda);
    }
    
    //ValidarCampo
    public static Integer mostrarErrorcampoIdAlumno(String campo){
        if(!campo.matches("\\d+")){
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
    
    public static void mostrarFinalizarInscripcion(ClaseDTO clase,String campo){
        
        Integer codigoAlumno = mostrarErrorcampoIdAlumno(campo);
        AlumnoBusquedaDTO alumnoBusqueda = new AlumnoBusquedaDTO(codigoAlumno);
        AlumnoDTO alumnoEncontrado = inscribirClase.obtenerAlumno(alumnoBusqueda);
        if(alumnoEncontrado == null){
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
    

}
