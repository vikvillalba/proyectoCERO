
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
    private List<ClaseDTO> clases;
    private List<AlumnoDTO> alumnos;

    public InscribirClase() {
        this.pagos = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
        this.clases = new ArrayList<>();
        this.alumnos = new ArrayList<>();
        
        // datos de prueba
        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY);
        ClaseDTO clase = new ClaseDTO(1, "Contemporaneo principiante", dias, LocalTime.of(18, 00), LocalTime.of(19, 15), "César Díaz", new BigDecimal(500.00));
        AlumnoDTO alumno = new AlumnoDTO(12345, "Gómez", "Pérez", "Juan", "123-456-7890", LocalDate.of(1995, 5, 20), "juan.gomez@example.com");
        clases.add(clase);
        alumnos.add(alumno);

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
        // TODO: cambiar validaciones 
//        List<String> clasesExistentes = Arrays.asList("Contemporanea", "danza","Folklore","ballet");
//        for (String clasesExistente : clasesExistentes) {
//           if(clasesExistente.toLowerCase().contains(nombre.toLowerCase())){
//               return true;
//           }
//           
//        }
        return true;
    }

    //// METODOS DE SELECC<ION DE CLASES : BUSQUEDAS
    @Override
    public List<ClaseDTO> buscarClasesPorNombre(String nombre) {
        List<ClaseDTO> clasesExistentes = new ArrayList<>();

        for (ClaseDTO clase : clases) {
            if (clase.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                clasesExistentes.add(clase);
            }
        }
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

    @Override
    public List<AlumnoDTO> obtenerAlumnosClase(){
        // hacer validaciones de que los alumnos si estén inscritos en la clase
        // esta no era mi chamba plebes que onda :( ..... 
        return alumnos;
    }
    
}

