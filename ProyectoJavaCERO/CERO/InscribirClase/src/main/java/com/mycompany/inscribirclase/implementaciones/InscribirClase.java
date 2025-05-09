package com.mycompany.inscribirclase.implementaciones;


import com.mycompany.InterfazBO.IAlumnosBO;
import com.mycompany.InterfazBO.IClasesBO;
import com.mycompany.InterfazBO.IInscripcionesBO;
import com.mycompany.InterfazBO.IPagosBO;
import com.mycompany.dtos.AlumnoBusquedaDTO;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NuevaInscripcionDTO;
import com.mycompany.dtos.NuevoPagoDTO;
import com.mycompany.dtos.PagoDTO;
import com.mycompany.infraestructura.sistemaPago.GestorPagosFachada;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import com.mycompany.inscribirclase.IInscribirClase;
import com.mycompany.negocio.Fabricas.FabricaObjetosNegocio;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author victoria
 */
public class InscribirClase implements IInscribirClase {

    private IClasesBO clasesBO;
    private IPagosBO pagosBO;
    private IInscripcionesBO inscripcionesBO;
    private IAlumnosBO alumnosBO;


    public InscribirClase() {
        this.pagosBO = FabricaObjetosNegocio.obtenerPagosBO();
        this.inscripcionesBO = FabricaObjetosNegocio.obtenerInscripcionesBO();
        this.clasesBO = FabricaObjetosNegocio.obtenerClasesBO();
        this.alumnosBO = FabricaObjetosNegocio.obtenerAlumnosBO();
        

      
    }

    @Override
    public BigDecimal calcularCambio(BigDecimal costoClase, BigDecimal cantidadRecibida) {
        return cantidadRecibida.subtract(costoClase);
    }

    @Override
    public PagoDTO realizarPagoEfectivo(NuevoPagoDTO nuevoPago) {
        return pagosBO.realizarPagoEfectivo(nuevoPago);

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
        return pagosBO.realizarPagoTarjeta(nuevoPago);
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
    public InscripcionDTO realizarInscripcionEfectivo(NuevaInscripcionDTO inscripcion) {
        AlumnoDTO alumno = inscripcion.getAlumno();
        PagoDTO pago = inscripcion.getPago();
        ClaseDTO clase = inscripcion.getClase();
        LocalDateTime fechaHora = inscripcion.getFecha();
        InscripcionDTO nuevaInscripcion = new InscripcionDTO(alumno, clase, fechaHora, pago);

        InscripcionDTO inscripcionRealizada = inscripcionesBO.registrarInscripcionPagoEfectivo(inscripcion);
        return inscripcionRealizada;
    }
    @Override
    public InscripcionDTO realizarInscripcionTarjeta(NuevaInscripcionDTO inscripcion) {
        AlumnoDTO alumno = inscripcion.getAlumno();
        PagoDTO pago = inscripcion.getPago();
        ClaseDTO clase = inscripcion.getClase();
        LocalDateTime fechaHora = inscripcion.getFecha();
        InscripcionDTO nuevaInscripcion = new InscripcionDTO(alumno, clase, fechaHora, pago);

        InscripcionDTO inscripcionRealizada = inscripcionesBO.registrarInscripcionPagoTarjeta(inscripcion);
        return inscripcionRealizada;
    }
    
    @Override
    public boolean validarNombreClase(String nombre) {
        List<ClaseDTO> clasesExistentes = clasesBO.obtenerClases();

        // Separar el nombre ingresado en palabras
        String[] palabras = nombre.trim().split("\\s+");

        for (String palabra : palabras) {
            for (ClaseDTO claseExistente : clasesExistentes) {
                // Comparación LIKE "%palabra%", ignorando mayúsculas y minúsculas
                if (claseExistente.getNombre().toLowerCase().contains(palabra.toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }

    //// METODOS DE SELECC<ION DE CLASES : BUSQUEDAS
    @Override
    public List<ClaseDTO> buscarClasesPorNombre(String nombre) {
        return clasesBO.obtenerClasesNombre(nombre);
    }

    @Override
    public boolean validarNombreClaseVacio(String nombre) {
        //Poner REGLAS DE NEGOCIO PARA LA BUSQUEDA DE NOMBRES CLASE
        if (nombre == null || nombre.trim().isEmpty() || nombre.equalsIgnoreCase("ingresa nombre clase...")) {
            return true;
        }
        return false;
    }

    @Override
    public List<AlumnoDTO> obtenerAlumnosClase() {
        // hacer validaciones de que los alumnos si estén inscritos en la clase
        List<AlumnoDTO> alumnos = alumnosBO.obtenerAlumnos();
        return alumnos;
    }

    @Override
    public AlumnoDTO obtenerAlumno(AlumnoBusquedaDTO alumnoBusqueda) {
        List<AlumnoDTO> alumnos = alumnosBO.obtenerAlumnos();

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

        AlumnoDTO alumnoAgregado = alumnosBO.agregarAlumno(alumnoDTO);
        return alumnoAgregado;
    }



}
