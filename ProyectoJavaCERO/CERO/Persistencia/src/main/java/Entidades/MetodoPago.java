package Entidades;

import implementaciones.ObjectIDMapper;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */

@BsonDiscriminator
public class MetodoPago {

    private ObjectId id;
    private String tipo;

    public MetodoPago() {
    }

    public MetodoPago(ObjectId id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @BsonIgnore
    public String getIdString() {
        return ObjectIDMapper.toString(id);
    }

}
