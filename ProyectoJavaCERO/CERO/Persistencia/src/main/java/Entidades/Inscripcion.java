package Entidades;

import implementaciones.ObjectIDMapper;
import java.time.LocalDateTime;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */
public class Inscripcion {

    private ObjectId id;
    private ObjectId clase;
    private ObjectId alumno;
    private LocalDateTime fechaInscripcion;
    private Pago pago;

    public Inscripcion(String id, String clase, String alumno, LocalDateTime fechaInscripcion, Pago pago) {
        this.id = ObjectIDMapper.toObjectId(id);
        this.clase = ObjectIDMapper.toObjectId(clase);
        this.alumno = ObjectIDMapper.toObjectId(alumno);
        this.fechaInscripcion = fechaInscripcion;
        this.pago = pago;
    }

    public Inscripcion() {
    }

    public Inscripcion(String clase, String alumno, LocalDateTime fechaInscripcion, Pago pago) {
        this.clase = ObjectIDMapper.toObjectId(clase);
        this.alumno = ObjectIDMapper.toObjectId(alumno);
        this.fechaInscripcion = fechaInscripcion;
        this.pago = pago;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getClase() {
        return clase;
    }

    public void setClase(ObjectId clase) {
        this.clase = clase;
    }

    public ObjectId getAlumno() {
        return alumno;
    }

    public void setAlumno(ObjectId alumno) {
        this.alumno = alumno;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    @BsonIgnore
    public String getIdString() {
        return ObjectIDMapper.toString(id);
    }

    @BsonIgnore
    public String getIdClaseString() {
        return ObjectIDMapper.toString(clase);
    }

    @BsonIgnore
    public String getIdAlumnoString() {
        return ObjectIDMapper.toString(alumno);
    }

}
