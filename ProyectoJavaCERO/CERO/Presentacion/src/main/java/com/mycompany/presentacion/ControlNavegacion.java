package com.mycompany.presentacion;

import FRMs.*;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.inscribirclase.IInscribirClase;
import com.mycompany.inscribirclase.implementaciones.InscribirClase;
import com.mycompany.negocio.dtos.AlumnoDTO;
import com.mycompany.negocio.dtos.ClaseDTO;
import com.mycompany.negocio.dtos.ClaseListaDTO;
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

    private static IInscribirClase inscribirClase;

    public ControlNavegacion() {
        this.inscribirClase = new InscribirClase();
    }

    /**
     * Muestra la pantalla de menú principal.
     */
    public static void mostrarMenuPrincipal() {
        FrmMenuPrincipal menu = new FrmMenuPrincipal();
        menu.setVisible(true);
    }

    /**
     * Muestra la pantalla que inicia el caso de uso para inscribir a un alumno.
     */
    //Se le pasa el control creado en el main para que solo tenga un control
    public static void mostrarInscribirClase(ControlNavegacion control) {
        FrmInscribirClase inscribirClase = new FrmInscribirClase(control);
        inscribirClase.setVisible(true);
    }

    /**
     * Muestra la pantalla para registrar un pago en efectivo para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarPagoEfectivo(ClaseDTO clase, AlumnoDTO alumno) {
        FrmPagoEfectivo pagoEfectivo = new FrmPagoEfectivo(clase, alumno);
        pagoEfectivo.setVisible(true);
    }

    /**
     * Muestra la pantalla para registrar un pago con tarjeta para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarPagoTarjeta(ClaseDTO clase, AlumnoDTO alumno) {
        FrmPagoTarjeta pagoTarjeta = new FrmPagoTarjeta(clase, alumno);
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
        }
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
    public static void mostrarDatosClase() {
        FrmDatosClase frmDatosClase = new FrmDatosClase();
        frmDatosClase.setVisible(true);
    }
    
    //MOSTRAR CLASES EXISTENTES
   
    public static void mostrarClasesExistentes(String nombre) throws PresentacionException{
        if(inscribirClase.validarNombreClaseVacio(nombre)== true){
            throw new PresentacionException("El campo esta vacio, porfavor ingrese el nombre de la clase a buscar");
        }
        if(inscribirClase.validarNombreClase(nombre) == false){
            throw new PresentacionException("El nombre de clase no existe"); 
        }
        NombreClaseParam nombreClase = new NombreClaseParam(nombre);
        List<ClaseListaDTO> clases = obtenerClaseLista(nombreClase.getNombreClase());
        FrmClasesExistentes frmClasesExistentes = new FrmClasesExistentes(clases);
        frmClasesExistentes.setVisible(true);
    }
    
    private static List<ClaseListaDTO> obtenerClaseLista(String nombre){
        return inscribirClase.buscarClasesPorNombre(nombre);
    }
    

}
