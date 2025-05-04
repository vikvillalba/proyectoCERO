/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.MetodoPagoEfectivo;
import Entidades.PagoEfectivo;
import Entidades.Pago;
import java.math.BigDecimal;
import java.time.LocalDate;
import InterfazDAOs.IPagosEfectivoDAO;

/**
 *
 * @author Usuario
 */
public class PagosEfectivoDAO implements IPagosEfectivoDAO {

    @Override
    public PagoEfectivo registrarPagoEfectivo(PagoEfectivo pagoefectivo) {
        MetodoPagoEfectivo metodoPagoEfectivoMock = new MetodoPagoEfectivo(
                1L, // id del método de pago
                new BigDecimal("1000.00"), // cantidad recibida
                new BigDecimal("200.00") // cambio
        );
        PagoEfectivo pagoMock = new PagoEfectivo(1L, new BigDecimal("500.00"), LocalDate.now(),metodoPagoEfectivoMock);
        return pagoMock;
    }
    
}
