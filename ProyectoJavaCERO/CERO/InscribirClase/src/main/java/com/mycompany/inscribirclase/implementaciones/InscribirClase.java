
package com.mycompany.inscribirclase.implementaciones;

import com.mycompany.dtos.AlumnoBusquedaDTO;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.MetodoPagoDTO;
import com.mycompany.dtos.NuevaInscripcionDTO;
import com.mycompany.dtos.NuevoPagoDTO;
import com.mycompany.dtos.PagoDTO;
import com.mycompany.infraestructura.sistemaPago.GestorPagosFachada;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.inscribirclase.IInscribirClase;
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
        ClaseDTO clase1 = new ClaseDTO(2, "Contemporaneo principiante", dias, LocalTime.of(18, 00), LocalTime.of(19, 15), "César Díaz", new BigDecimal(500.00));
        AlumnoDTO alumno = new AlumnoDTO(1, "Gómez", "Pérez", "Juan", "123-456-7890", LocalDate.of(1995, 5, 20), "juan.gomez@example.com");
        clases.add(clase);
        clases.add(clase1);
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

        // llamar a la BO de pagos
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

    // Conexión con el subsistema de infraestructura
    @Override
    public PagoRealizadoDTO confirmarPagoTarjeta(NuevoPagoTarjetaDTO pagoTarjeta) {
        GestorPagosFachada gestorPagos = new GestorPagosFachada();
        return gestorPagos.registrarPago(pagoTarjeta);
    }

    // guarda el pago ya que el sistema externo lo valido
    @Override
    public PagoDTO realizarPagoTarjeta(NuevoPagoDTO nuevoPago) {
        Random random = new Random();

        BigDecimal total = nuevoPago.getTotal();
        MetodoPagoDTO metodoPago = nuevoPago.getMetodoPago();
        int codigo = random.nextInt(1000) + 1;
        LocalDateTime fecha = LocalDateTime.now();
        PagoDTO pago = new PagoDTO(codigo, total, metodoPago, fecha, true);

        // llamar a la BO de pagos
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

        // llamar a la BO de inscripciones
        inscripciones.add(nuevaInscripcion);
        return nuevaInscripcion;
    }

    @Override
    public boolean validarNombreClase(String nombre) {
        // TODO: cambiar validaciones 
        // QUE VALIDE NOMBRES POR EL NOMBRE DE CLASES QUE EXISTEN Y QUE TENGAN DE SEMEJANZA MINIMO UNA PALABRA COMPLETA MAS DE 5 LETRAS
        List<String> clasesExistentes = Arrays.asList("Contemporaneo", "danza","Folklore","ballet");
        for (String clasesExistente : clasesExistentes) {
           if(clasesExistente.equalsIgnoreCase(nombre.trim())){
               return true;
           }
           
        }
        return false;
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
        return alumnos;
    }

    @Override
    public AlumnoDTO obtenerAlumno(AlumnoBusquedaDTO alumnoBusqueda) {
        
        for (AlumnoDTO alumno : alumnos) {
            if (alumno.getCodigo() == alumnoBusqueda.getCodigo()) {
                return alumno;
            }
        }
        return null; // Retorna null si no se encuentra el alumno
    }

    @Override
    public boolean validarApellidoPaterno(String apellidoPaterno) {
        if (apellidoPaterno != null) {
            return apellidoPaterno.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñüÜ]+$");
        } else {
            return false;
        }
    }

    @Override
    public boolean validarApellidoMaterno(String apellidoMaterno) {
        if (apellidoMaterno != null) {
            return apellidoMaterno.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñüÜ]+$");
        } else {
            return false;
        }
    }

    @Override
    public boolean validarNombreAlumno(String nombre) {
        if (nombre != null) {
            return nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñüÜ\\s]+$");
        } else {
            return false;
        }
    }

    @Override
    public boolean validarTelefonoAlumno(String telefono) {
        if (telefono != null) {
            return telefono.matches("^[0-9]{10}$");
        } else {
            return false;
        }
    }

    @Override
    public boolean validarFechaNacimientoAlumno(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean validarCorreoElectronicoAlumno(String correo) {
        if (correo != null) {
            return correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        } else {
            return false;
        }
    }

    @Override
    public AlumnoDTO agregarAlumno(AlumnoDTO alumnoDTO) {
        Random random = new Random();
        int codigo = random.nextInt(1000) + 1;
        
        alumnoDTO.setCodigo(codigo);
        
        // llamar a la BO de alumnos
        alumnos.add(alumnoDTO);
        return alumnoDTO;
    }

}
