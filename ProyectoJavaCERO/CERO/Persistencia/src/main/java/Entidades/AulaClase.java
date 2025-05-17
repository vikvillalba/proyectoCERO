/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public class AulaClase {
    private ObjectId id;
    private String nombreAula;
    private List<Clase> clasesPresenciales;

    public AulaClase() {
    }

    public AulaClase(ObjectId id, String nombreAula, List<Clase> clasesPresenciales) {
        this.id = id;
        this.nombreAula = nombreAula;
        this.clasesPresenciales = clasesPresenciales;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombreAula() {
        return nombreAula;
    }

    public void setNombreAula(String nombreAula) {
        this.nombreAula = nombreAula;
    }

    public List<Clase> getClasesPresenciales() {
        return clasesPresenciales;
    }

    public void setClasesPresenciales(List<Clase> clasesPresenciales) {
        this.clasesPresenciales = clasesPresenciales;
    }

    
}
