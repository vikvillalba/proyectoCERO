package Entidades;

import implementaciones.ObjectIDMapper;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

/**
 *
 * @author Usuario
 */
public abstract class MetodoPago {

    private ObjectId id;

    public MetodoPago() {
    }

    public MetodoPago(ObjectId id) {
        this.id = id;
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
