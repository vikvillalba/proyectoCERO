package com.mycompany.presentacion;

import FRMs.*;
import com.mycompany.inscribirclase.IInscribirClase;
import com.mycompany.inscribirclase.implementaciones.InscribirClase;
import com.mycompany.negocio.dtos.AlumnoDTO;
import com.mycompany.negocio.dtos.ClaseDTO;
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

    public static IInscribirClase getInscribirClase() {
        return inscribirClase;
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
    public static void mostrarInscribirClase() {
        FrmInscribirClase inscribirClase = new FrmInscribirClase();
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

    /**
     * Muestra mensaje de error cuando hay efectivo faltante para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarMensajeErrorEfectivoFaltante(JFrame parentComponent) {
        JOptionPane.showConfirmDialog(parentComponent, "El efectivo ingresado no es suficiente para realizar el pago.",
                "Efectivo faltante", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Muestra mensaje de error cuando el numero de cuenta de tarjeta es inválido para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarMensajeErrorNumeroCuentaInvalido(JFrame parentComponent) {
        JOptionPane.showConfirmDialog(parentComponent, "El numero de cuenta es inválido. Intente nuevamente ",
                "Número de cuenta inválido", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Muestra mensaje de error cuando el propietario de tarjeta es inválido para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarMensajeErrorPropietarioTarjetaInvalido(JFrame parentComponent) {
        JOptionPane.showConfirmDialog(parentComponent, "El propietario es un texto inválido. Intente nuevamente ",
                "Propietario inválido", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

    }
    /**
     * Muestra mensaje de error cuando el cvv de tarjeta es inválido para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarMensajeErrorCVVInvalido(JFrame parentComponent) {
        JOptionPane.showConfirmDialog(parentComponent, "El CVV no puede estar vacío, ser menor a 3 dígitos o mayor a 4. Intente nuevamente ",
                "CVV inválido", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

    }
    
    /**
     * Muestra mensaje de error cuando la fecha de vencimiento de tarjeta es inválido para el caso de uso de inscribir a un alumno.
     */
    public static void mostrarMensajeFechaVencimientoInvalida(JFrame parentComponent) {
        JOptionPane.showConfirmDialog(parentComponent, "La fecha de vencimiento es inválida. Intente nuevamente ",
                "Fecha de vencimiento inválida", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

    }
    
    /**
     * Muestra pantalla de DatosClase
     */
    public static void mostrarDatosClase() {
        FrmDatosClase frmDatosClase = new FrmDatosClase();
        frmDatosClase.setVisible(true);
    }
    
}
