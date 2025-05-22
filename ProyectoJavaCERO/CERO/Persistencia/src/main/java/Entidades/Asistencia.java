package Entidades;

import implementaciones.ObjectIDMapper;
import java.time.LocalDateTime;
import java.util.Date;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

/**
 * Representación de una asistencia en el sistema
 *
 * @author victoria
 */
public class Asistencia {

    private ObjectId id;
    private String tipoAsistencia;
    private LocalDateTime fechaHora;
    private ObjectId alumno;
    private ObjectId clase;
    private Justificante justificante;

    public Asistencia() {
    }

    public Asistencia(String tipoAsistencia, LocalDateTime fechaHora, String alumno, String clase) {
        this.tipoAsistencia = tipoAsistencia;
        this.fechaHora = fechaHora;
        this.alumno = ObjectIDMapper.toObjectId(alumno);
        this.clase = ObjectIDMapper.toObjectId(clase);
    }

    public Asistencia(String id, String tipoAsistencia, LocalDateTime fechaHora, String alumno, String clase) {
        this.id = ObjectIDMapper.toObjectId(id);
        this.tipoAsistencia = tipoAsistencia;
        this.fechaHora = fechaHora;
        this.alumno = ObjectIDMapper.toObjectId(alumno);
        this.clase = ObjectIDMapper.toObjectId(clase);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTipoAsistencia() {
       return tipoAsistencia;
    }

      public void setTipoAsistencia(String tipo) {
        this.tipoAsistencia = tipo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public ObjectId getAlumno() {
        return alumno;
    }

    public void setAlumno(ObjectId alumno) {
        this.alumno = alumno;
    }

    public ObjectId getClase() {
        return clase;
    }

    public void setClase(ObjectId clase) {
        this.clase = clase;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    @BsonIgnore
    public String getIdString() {
        return ObjectIDMapper.toString(id);
    }

    @BsonIgnore
    public String getIdAlumnoString() {
        return ObjectIDMapper.toString(alumno);
    }

    @BsonIgnore
    public String getIdClaseString() {
        return ObjectIDMapper.toString(clase);
    }

    @BsonIgnore
    public void setIdString(String id) {
        this.id = ObjectIDMapper.toObjectId(id);
    }

}
