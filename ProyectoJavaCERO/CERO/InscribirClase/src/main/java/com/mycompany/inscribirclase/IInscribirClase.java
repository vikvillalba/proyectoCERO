package com.mycompany.inscribirclase;

import com.mycompany.dtos.AlumnoBusquedaDTO;
import com.mycompany.dtos.AlumnoDTO;
import com.mycompany.dtos.ClaseDTO;
import com.mycompany.dtos.InscripcionDTO;
import com.mycompany.dtos.NuevaInscripcionDTO;
import com.mycompany.dtos.NuevoPagoDTO;
import com.mycompany.dtos.PagoDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.PagoRealizadoDTO;
import com.mycompany.infraestructura.sistemaPago.implementaciones.NuevoPagoTarjetaDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz que abstrae las operaciones para el caso de uso de inscribir alumnos en una clase.
 *
 * @author victoria
 */
public interface IInscribirClase {

    /**
     * valida el efectivo recibido.
     */
    public abstract boolean validarEfectivoRecibido(BigDecimal costoClase, BigDecimal efectivo);

    /**
     * Calcula el cambio para el pago en efectivo.
     */
    public abstract BigDecimal calcularCambio(BigDecimal costoClase, BigDecimal cantidadRecibida);

    /**
     * Procesa el pago en efectivo.
     */
    public abstract PagoDTO realizarPagoEfectivo(NuevoPagoDTO nuevoPago);

    /**
     * Crea conexión al sistema de pagos en infraestructura.
     */
    public abstract PagoRealizadoDTO confirmarPagoTarjeta(NuevoPagoTarjetaDTO pagoTarjeta);

    /**
     * registra el pago con tarjeta.
     */
    public abstract PagoDTO realizarPagoTarjeta(NuevoPagoDTO nuevoPago);

    /**
     * valida que el numero de cuenta sea de 16 caracteres, y que no esté vacío.
     */
    public abstract boolean validarNumeroCuenta(String numeroCuenta);

    /**
     * valida que el propietario no esté vacío.
     */
    public abstract boolean validarPropietarioTarjeta(String propietario);

    /**
     * valida que la tarjeta no esté expirada, y que no esté vacía.
     */
    public abstract boolean validarFechaExpiracion(LocalDate fecha);

    /**
     * valida que el cvv sea de 4 dígitos, y que no esté vacío.
     */
    public abstract boolean validarCVV(int cvv);

    /**
     * valida que el apellidoPaterno no tenga números y que no esté vacío.
     */
    public abstract boolean validarApellidoPaterno(String apellidoPaterno);

    /**
     * valida que el apellidoMaterno no tenga números y que no esté vacío.
     */
    public abstract boolean validarApellidoMaterno(String apellidoMaterno);

    /**
     * valida que el nombre no tenga números y que no esté vacío.
     */
    public abstract boolean validarNombreAlumno(String nombre);

    /**
     * valida que el número tenga 10 dígitos y que no esté vacío.
     */
    public abstract boolean validarTelefonoAlumno(String telefono);

    /**
     * valida que la fecha no sea mayor al día actual y que no esté vacía.
     */
    public abstract boolean validarFechaNacimientoAlumno(LocalDate fechaNacimiento);

    /**
     * valida que el correo tenga un arroba, que el dominio tenga al menos 2 letras y que no esté vacío.
     */
    public abstract boolean validarCorreoElectronicoAlumno(String correo);

    /**
     * Agrega a un nuevo Alumno
     */
    public abstract AlumnoDTO agregarAlumno(AlumnoDTO alumnoDTO);

    /**
     * Procesa una nueva Inscripción con un pago en efectivo.
     */
    public abstract InscripcionDTO realizarInscripcionEfectivo(NuevaInscripcionDTO inscripcion);

    /**
     * Procesa una nueva Inscripción con un pago en línea.
     */
    public abstract InscripcionDTO realizarInscripcionTarjeta(NuevaInscripcionDTO inscripcion);

    /**
     * Validar Clase por su nombre.
     */
    public abstract boolean validarNombreClase(String nombre);

    //METODOS DE SELECCION DE CLASES :BUSQUEDAS
    public abstract List<ClaseDTO> buscarClasesPorNombre(String nombre);

    public abstract boolean validarNombreClaseVacio(String nombre);

    public List<AlumnoDTO> obtenerAlumnosClase();

    public AlumnoDTO obtenerAlumno(AlumnoBusquedaDTO alumnoBusqueda);

}
