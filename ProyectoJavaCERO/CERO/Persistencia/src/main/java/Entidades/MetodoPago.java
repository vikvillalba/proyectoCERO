package Entidades;

/**
 *
 * @author Usuario
 */
public abstract class MetodoPago {
    private Integer id;

    public MetodoPago() {
    }

    public MetodoPago(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
     
    
}
