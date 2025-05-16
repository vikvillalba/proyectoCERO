/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.GestionarClases;

/**
 *
 * @author Jack Murrieta
 */
public class AlumnoInscritoDTO {
    private int numeroLista;
    private String alumnoNombre;

    public AlumnoInscritoDTO() {
    }

    public AlumnoInscritoDTO(int numeroLista, String alumnoNombre) {
        this.numeroLista = numeroLista;
        this.alumnoNombre = alumnoNombre;
    }

    public int getNumeroLista() {
        return numeroLista;
    }

    public void setNumeroLista(int numeroLista) {
        this.numeroLista = numeroLista;
    }

    public String getAlumnoNombre() {
        return alumnoNombre;
    }

    public void setAlumnoNombre(String alumnoNombre) {
        this.alumnoNombre = alumnoNombre;
    }
    
}
