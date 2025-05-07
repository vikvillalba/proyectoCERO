
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Alumno {
    private Integer codigo;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String correoElectronico;

    public Alumno(Integer codigo, String apellidoPaterno, String apellidoMaterno, String nombre, String telefono, LocalDate fechaNacimiento, String correoElectronico) {
        this.codigo = codigo;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
    }


}
