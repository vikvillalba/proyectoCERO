package Entidades;

import implementaciones.ObjectIDMapper;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;

/**
 * Representación de una asistencia en el sistema
 *
 * @author victoria
 */
public class Asistencia {

    private ObjectId id;
    private TipoAsistencia tipoAsistencia;
    private LocalDateTime fechaHora;
    private String alumno;
    private String clase;
    private Justificante justificante;

    public Asistencia() {
    }

    public Asistencia(TipoAsistencia tipoAsistencia, LocalDateTime fechaHora, String alumno, String clase) {
        this.tipoAsistencia = tipoAsistencia;
        this.fechaHora = fechaHora;
        this.alumno = alumno;
        this.clase = clase;
    }

    public Asistencia(String id, TipoAsistencia tipoAsistencia, LocalDateTime fechaHora, String alumno, String clase) {
        this.id = ObjectIDMapper.toObjectId(id);
        this.tipoAsistencia = tipoAsistencia;
        this.fechaHora = fechaHora;
        this.alumno = alumno;
        this.clase = clase;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public TipoAsistencia getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(TipoAsistencia tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    public String getIdString() {
        return ObjectIDMapper.toString(id);
    }

}
