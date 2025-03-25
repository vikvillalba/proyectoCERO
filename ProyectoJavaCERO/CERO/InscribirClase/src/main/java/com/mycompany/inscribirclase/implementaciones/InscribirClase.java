
package com.mycompany.inscribirclase.implementaciones;

import com.mycompany.infraestructura.sistemaPago.IGestorPagos;
import com.mycompany.infraestructura.sistemaPago.implementaciones.GestorPagos;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.inscribirclase.IInscribirClase;
import com.mycompany.negocio.dtos.AlumnoDTO;
import com.mycompany.negocio.dtos.ClaseDTO;
import com.mycompany.negocio.dtos.ClaseListaDTO;
import com.mycompany.negocio.dtos.InscripcionDTO;
import com.mycompany.negocio.dtos.MetodoPagoDTO;
import com.mycompany.negocio.dtos.NombreClaseParam;
import com.mycompany.negocio.dtos.NuevaInscripcionDTO;
import com.mycompany.negocio.dtos.NuevoPagoDTO;
import com.mycompany.negocio.dtos.PagoDTO;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author victoria
 */
public class InscribirClase implements IInscribirClase {

    private List<PagoDTO> pagos;
    private List<InscripcionDTO> inscripciones;
    private ClaseDTO clase;
    private List<ClaseDTO> clases;

    public InscribirClase() {
        this.pagos = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
        clase = new ClaseDTO();
        this.clases = clase.setClases();
        this.clases = clase.setClases();
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
        PagoDTO pago = new PagoDTO(codigo, total, metodoPago, fecha, true);

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
        PagoDTO pago = new PagoDTO(codigo, total, metodoPago, fecha, true);

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

    @Override
    public boolean validarNombreClase(String nombre) {
        // Lista de Nombres que pueden tener un nombreClase
        List<String> clasesExistentes = Arrays.asList("Contemporanea", "danza","Folklore","ballet");
        for (String clasesExistente : clasesExistentes) {
           if(clasesExistente.toLowerCase().contains(nombre.toLowerCase())){
               return true;
           }
           
        }
        return false;
    }

    //// METODOS DE SELECCION DE CLASES : BUSQUEDAS
    @Override
    public List<ClaseListaDTO> buscarClasesPorNombre(String nombre) {
        List<ClaseListaDTO> clasesExistentes = new ArrayList<>();
        ClaseListaDTO claseListaDto = new ClaseListaDTO();
        
        for (ClaseDTO clase : clases) {
            if (clase.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                claseListaDto = convertirClaseListaDTO(clase);
                clasesExistentes.add(claseListaDto);
            }
        }
        claseListaDto.setClasesExistentes(clasesExistentes);
        return clasesExistentes;
    }

    private ClaseListaDTO convertirClaseListaDTO(ClaseDTO clase){
        String id = String.valueOf(clase.getCodigo());
        String nombreClase = clase.getNombre();
        String horaInicio = String.valueOf(clase.getHoraInicio());
        String horaFin = String.valueOf(clase.getHoraFin());
        String dias = String.valueOf(clase.getDias());
        String maestro = clase.getMaestro();
        return new ClaseListaDTO(id, nombreClase, horaInicio, horaFin, dias, maestro); 
    }

    @Override
    public boolean validarNombreClaseVacio (String nombre) {
        //Poner REGLAS DE NEGOCIO PARA LA BUSQUEDA DE NOMBRES CLASE
        if (nombre == null || nombre.trim().isEmpty() || nombre.equalsIgnoreCase("ingresa nombre clase...")) {
            return true;
        }
        return false;
    }

    
}

