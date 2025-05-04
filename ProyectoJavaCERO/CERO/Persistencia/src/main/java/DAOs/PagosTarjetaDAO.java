
package DAOs;

import Entidades.MetodoPagoTarjeta;
import InterfazDAOs.IPagosTarjetaDAO;
import java.util.List;

/**
 *
 * @author victoria
 */
public class PagosTarjetaDAO implements IPagosTarjetaDAO{

    private List<MetodoPagoTarjeta> pagosRealizados;
    
    @Override
    public MetodoPagoTarjeta registrarPago(MetodoPagoTarjeta pago) {
       this.pagosRealizados.add(pago);
       return pago;
    }
    
}
