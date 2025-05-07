package Entidades;

/**
 *
 * @author Usuario
 */
public abstract class MetodoPago {
    private Long id;

    public MetodoPago(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
     
    
}
