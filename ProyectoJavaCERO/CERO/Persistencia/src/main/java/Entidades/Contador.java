package Entidades;

import org.bson.codecs.pojo.annotations.BsonId;

/**
 * Entidad que gestiona los códigos integer para Clase y Alumno.
 * @author victoria
 */
public class Contador {
    @BsonId
    private String nombreEntidad;
    private Integer codigoSecuencia;

    public Contador() {
    }

    public Contador(String nombreEntidad, Integer codigoSecuencia) {
        this.nombreEntidad = nombreEntidad;
        this.codigoSecuencia = codigoSecuencia;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public Integer getCodigoSecuencia() {
        return codigoSecuencia;
    }

    public void setCodigoSecuencia(Integer codigoSecuencia) {
        this.codigoSecuencia = codigoSecuencia;
    }
    
    
}
