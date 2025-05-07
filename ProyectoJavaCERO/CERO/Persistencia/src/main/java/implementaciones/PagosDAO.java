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

/**
 *
 * @author Usuario
 */
public class PagosDAO implements IPagosDAO {

    @Override
    public Pago registrarPago(Pago pago) {

        MetodoPagoTarjeta metodoPagoTarjetaMock = new MetodoPagoTarjeta(
                2L,
                "CONFIRM123456",
                LocalDateTime.now()
        );
        Pago pagoMock = new Pago(1L, new BigDecimal("500.00"), LocalDate.now(), true, metodoPagoTarjetaMock); 
        return pagoMock;
    }
    
}
