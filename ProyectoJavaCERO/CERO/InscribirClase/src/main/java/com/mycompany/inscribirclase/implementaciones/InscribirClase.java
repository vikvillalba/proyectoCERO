
package com.mycompany.inscribirclase.implementaciones;

import com.mycompany.infraestructura.sistemaPago.IGestorPagos;
import com.mycompany.infraestructura.sistemaPago.implementaciones.GestorPagos;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.inscribirclase.IInscribirClase;
import com.mycompany.negocio.dtos.AlumnoDTO;
import com.mycompany.negocio.dtos.ClaseDTO;
import com.mycompany.negocio.dtos.InscripcionDTO;
import com.mycompany.negocio.dtos.MetodoPagoDTO;
import com.mycompany.negocio.dtos.NombreClaseParam;
import com.mycompany.negocio.dtos.NuevaInscripcionDTO;
import com.mycompany.negocio.dtos.NuevoPagoDTO;
import com.mycompany.negocio.dtos.PagoDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JTextField;

/**
 *
 * @author victoria
 */
public class InscribirClase implements IInscribirClase{
    
    private List<PagoDTO> pagos;
    private List<InscripcionDTO> inscripciones;

    public InscribirClase() {
        this.pagos = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
    }
    
    

    @Override
    public BigDecimal calcularCambio(BigDecimal costoClase, BigDecimal cantidadRecibida) {
        return cantidadRecibida.subtract(costoClase);
    }

    @Override
    public PagoDTO realizarPagoEfectivo(NuevoPagoDTO nuevoPago) {
        Random random = new Random();
        
        BigDecimal total = nuevoPago.getTotal();
        MetodoPagoDTO metodoPago = nuevoPago.getMetodoPago();
        int codigo = random.nextInt(1000) + 1;
        LocalDateTime fecha = LocalDateTime.now();
        PagoDTO pago =  new PagoDTO(codigo, total, metodoPago, fecha, true);
        
        pagos.add(pago);
        return pago;
        
    }


    @Override
    public boolean validarEfectivoRecibido(BigDecimal costoClase, BigDecimal efectivo) {
        if (costoClase == null || efectivo == null) {
            return false; // No se puede validar si hay valores nulos
        }
        return efectivo.compareTo(costoClase) >= 0 && efectivo.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public PagoRealizadoDTO confirmarPagoTarjeta(NuevoPagoTarjetaDTO pagoTarjeta) {
        IGestorPagos gestorPagosExterno = new GestorPagos();
        return gestorPagosExterno.registrarPago(pagoTarjeta);
    }

    @Override
    public PagoDTO realizarPagoTarjeta(NuevoPagoDTO nuevoPago) {
        Random random = new Random();
        
        BigDecimal total = nuevoPago.getTotal();
        MetodoPagoDTO metodoPago = nuevoPago.getMetodoPago();
        int codigo = random.nextInt(1000) + 1;
        LocalDateTime fecha = LocalDateTime.now();
        PagoDTO pago =  new PagoDTO(codigo, total, metodoPago, fecha, true);
        
        pagos.add(pago);
        return pago;
    }

    @Override
    public boolean validarNumeroCuenta(String numeroCuenta) {
        return numeroCuenta != null
                && !numeroCuenta.isEmpty()
                && numeroCuenta.length() == 16
                && numeroCuenta.matches("\\d{16}");
    }

    @Override
    public boolean validarPropietarioTarjeta(String propietario) {
        return propietario != null && !propietario.trim().isEmpty();
    }

    @Override
    public boolean validarFechaExpiracion(LocalDate fecha) {
        return fecha != null && fecha.isAfter(LocalDate.now());
    }

    @Override
    public boolean validarCVV(int cvv) {
         return cvv >= 100 && cvv <= 9999;
    }

    @Override
    public InscripcionDTO realizarInscripcion(NuevaInscripcionDTO inscripcion) {
        Random random = new Random();
        AlumnoDTO alumno = inscripcion.getAlumno();
        PagoDTO pago = inscripcion.getPago();
        ClaseDTO clase = inscripcion.getClase();
        LocalDateTime fechaHora = inscripcion.getFecha();
        int codigo = random.nextInt(1000) + 1;
        InscripcionDTO nuevaInscripcion = new InscripcionDTO(codigo, alumno, clase, fechaHora, pago);
        
        inscripciones.add(nuevaInscripcion);
        return nuevaInscripcion;
    }
    
    ////METODOS DE SELECCION DE CLASES : BUSQUEDAS
    @Override
    public List<ClaseDTO> buscarClasesPorNombre(String nombre) {
        
        
    }

    @Override
    public boolean validarNombreClase(NombreClaseParam nombre) {
        String nombreClase = nombre.getNombreClase();
        //Poner REGLAS DE NEGOCIO PARA LA BUSQUEDA DE NOMBRES CLASE
        return true;
    }
    
}
