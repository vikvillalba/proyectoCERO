package implementaciones;

import org.bson.types.ObjectId;

/**
 *
 * @author victoria
 */
public class ObjectIDMapper {
    
     public static String toString(ObjectId objectId) {
        return objectId != null ? objectId.toHexString() : null;
    }

    public static ObjectId toObjectId(String id) {
        return (id != null && ObjectId.isValid(id)) ? new ObjectId(id) : null;
    }
}
