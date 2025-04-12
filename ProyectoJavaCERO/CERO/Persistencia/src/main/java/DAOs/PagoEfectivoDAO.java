/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.MetodoPagoEfectivo;
import Entidades.PagoEfectivoEntidad;
import Entidades.PagoEntidad;
import InterfazDAOs.IPagoEfectivoDAO;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class PagoEfectivoDAO implements IPagoEfectivoDAO {

    @Override
    public PagoEfectivoEntidad registrarPagoEfectivo(PagoEfectivoEntidad pagoefectivo) {
        MetodoPagoEfectivo metodoPagoEfectivoMock = new MetodoPagoEfectivo(
                1L, // id del método de pago
                new BigDecimal("1000.00"), // cantidad recibida
                new BigDecimal("200.00") // cambio
        );
        PagoEfectivoEntidad pagoMock = new PagoEfectivoEntidad(1L, new BigDecimal("500.00"), LocalDate.now(),metodoPagoEfectivoMock);
        return pagoMock;
    }
    
}
