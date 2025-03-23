
package com.mycompany.presentacion;

import FRMs.*;
import com.mycompany.inscribirclase.implementaciones.InscribirClase;
import com.mycompany.negocio.dtos.ClaseDTO;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author victoria
 */
public class ControlNavegacion {
    
    private static InscribirClase inscribirClase;

    public ControlNavegacion() {
        this.inscribirClase = new InscribirClase();
    }

    public static InscribirClase getInscribirClase() {
        return inscribirClase;
    }
    
    
    /** Muestra la pantalla de menú principal. */
    public static void mostrarMenuPrincipal(){
        FrmMenuPrincipal menu = new FrmMenuPrincipal();
        menu.setVisible(true);
    }
    /** Muestra la pantalla que inicia el caso de uso para inscribir a un alumno. */
    public static void mostrarInscribirClase(){
        FrmInscribirClase inscribirClase = new FrmInscribirClase();
        inscribirClase.setVisible(true);
    }
    
     /** Muestra la pantalla para registrar un pago en efectivo para el caso de uso de inscribir a un alumno. */
    public static void mostrarPagoEfectivo(ClaseDTO clase){
        FrmPagoEfectivo pagoEfectivo = new FrmPagoEfectivo(clase);
        pagoEfectivo.setVisible(true);
    }
    
     /** Muestra la pantalla para registrar un pago con tarjeta para el caso de uso de inscribir a un alumno. */
    public static void mostrarPagoTarjeta(ClaseDTO clase){
        FrmPagoTarjeta pagoTarjeta = new FrmPagoTarjeta(clase);
        pagoTarjeta.setVisible(true);
    }
    
    public static void mostrarMensajePagoExitoso(JFrame parentComponent){
             int response = JOptionPane.showConfirmDialog(parentComponent, "El pago se ha realizado exitosamente :)", 
                "Pago exitoso", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        
        if (response == JOptionPane.OK_OPTION) {
            // Si el usuario hace clic en OK, cierra la ventana raiz
            parentComponent.dispose();
        }
    }

    public static void mostrarMensajeErrorEfectivoFaltante(JFrame parentComponent) {
        JOptionPane.showConfirmDialog(parentComponent, "El efectivo ingresado no es suficiente para realizar el pago.",
                "Efectivo faltante", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

    }

}
