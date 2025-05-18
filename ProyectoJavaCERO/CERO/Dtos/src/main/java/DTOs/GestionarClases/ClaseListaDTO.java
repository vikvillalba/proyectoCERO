/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.GestionarClases;

/**
 *
 * @author Jack Murrieta
 */
public class ClaseListaDTO {
    private Integer codigo;
    private String nombreClase;
    private String horario;
    private String nombreMaestro;
    private int cupo;
    private String periodo;
    private String nombreAula;
    private boolean activa;

    public ClaseListaDTO(Integer codigo, String nombreClase, String horario, String nombreMaestro, int cupo, String periodo, String nombreAula, boolean activa) {
        this.codigo = codigo;
        this.nombreClase = nombreClase;
        this.horario = horario;
        this.nombreMaestro = nombreMaestro;
        this.cupo = cupo;
        this.periodo = periodo;
        this.nombreAula = nombreAula;
        this.activa = activa;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombreMaestro() {
        return nombreMaestro;
    }

    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    
}
