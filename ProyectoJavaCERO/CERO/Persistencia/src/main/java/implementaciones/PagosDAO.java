/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementaciones;

import Entidades.MetodoPagoTarjeta;
import Entidades.Pago;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import DAOs.IPagosDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PagosDAO implements IPagosDAO {
    private List<Pago> pagos;
    private Integer codigoPago = 1;

    public PagosDAO() {
        this.pagos = new ArrayList<>();
    }
    
    

    @Override
    public Pago registrarPago(Pago pago) {
        pago.setId(codigoPago);
        codigoPago++;
        pagos.add(pago);
        pago.setRealizado(true);
        return pago;

    }
    
}
