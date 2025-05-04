/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.MetodoPagoTarjeta;
import Entidades.PagoEntidad;
import InterfazDAOs.IPagoDAO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Usuario
 */
public class PagoDAO implements IPagoDAO {

    @Override
    public PagoEntidad registrarPago(PagoEntidad pago) {

        MetodoPagoTarjeta metodoPagoTarjetaMock = new MetodoPagoTarjeta(
                2L,
                "CONFIRM123456",
                LocalDateTime.now()
        );
        PagoEntidad pagoMock = new PagoEntidad(1L, new BigDecimal("500.00"), LocalDate.now(), true, metodoPagoTarjetaMock); 
        return pagoMock;
    }
    
}
