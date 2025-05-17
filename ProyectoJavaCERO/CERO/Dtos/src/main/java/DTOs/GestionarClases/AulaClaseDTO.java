/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs.GestionarClases;

/**
 *
 * @author Jack Murrieta
 */
public class AulaClaseDTO {
    private String idAula;
    private String nombreAula;

    public AulaClaseDTO(String idAula, String nombreAula) {
        this.idAula = idAula;
        this.nombreAula = nombreAula;
    }

    public AulaClaseDTO() {
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }
    
    @Override
    public String toString() {
        return getNombreAula();
    }

    
}
