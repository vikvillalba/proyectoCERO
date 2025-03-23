
package com.mycompany.infraestructura.sistemaPago.implementaciones;

import com.mycompany.infraestructura.sistemaPago.IGestorPagos;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un gestor de pagos externo
 * @author victoria
 */
public class GestorPagos implements IGestorPagos {
    
    private List<Tarjeta> tarjetas;
    private List<PagoRealizadoDTO> pagos;
    
    // para generar los codigos de confirmacion de los pagos 
    private final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final SecureRandom random = new SecureRandom();
    private final int LONGITUD_CODIGO = 8;

    public GestorPagos() {
      Tarjeta tarjeta1 = new Tarjeta("1234567890123456", "Juan Pérez",
                LocalDate.of(2027, Month.DECEMBER, 31), 123, 5000.0f);

        Tarjeta tarjeta2 = new Tarjeta("9876543210987654", "María López",
                LocalDate.of(2026, Month.JUNE, 30), 456, 2500.5f);

        Tarjeta tarjeta3 = new Tarjeta("5678123498761234", "Jesús Gómez",
                LocalDate.of(2028, Month.APRIL, 15), 789, 10000.75f);

        Tarjeta tarjeta4 = new Tarjeta("1111222233334444", "Ana Torres",
                LocalDate.of(2025, Month.SEPTEMBER, 10), 321, 750.0f);
        
        this.tarjetas = new ArrayList<>();
        this.pagos = new ArrayList<>();
        
        tarjetas.add(tarjeta1);
        tarjetas.add(tarjeta2);
        tarjetas.add(tarjeta3);
        tarjetas.add(tarjeta4);
    }

    private Tarjeta obtenerTarjeta(String numeroCuenta) {
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getNumeroCuenta().equals(numeroCuenta)) { 
                return tarjeta;
            }
        }
        return null;
    }

    private boolean validarSaldoTarjeta(Float cantidad, Tarjeta tarjeta) {
        if (cantidad == null || tarjeta == null || tarjeta.getSaldo() == null) {
            return false; 
        }
        return tarjeta.getSaldo().compareTo(cantidad) >= 0;
    }

    // generar codigo de confirmacion
    private String generarCodigo() {
        StringBuilder codigo = new StringBuilder(LONGITUD_CODIGO);
        for (int i = 0; i < LONGITUD_CODIGO; i++) {
            int index = random.nextInt(CARACTERES.length());
            codigo.append(CARACTERES.charAt(index));
        }
        return codigo.toString();
    }
    
    public PagoRealizadoDTO registrarPago(NuevoPagoTarjetaDTO pago){
        
        Tarjeta tarjeta = obtenerTarjeta(pago.getNumeroCuenta());
        LocalDateTime fecha = LocalDateTime.now(); // generar la fecha de registro
        
        if(tarjeta != null && validarSaldoTarjeta(pago.getMonto().floatValue(), tarjeta)){
            PagoRealizadoDTO pagoRealizado = new PagoRealizadoDTO(fecha, generarCodigo()); //armar pagorealizadodto
            pagos.add(pagoRealizado);  // agregar el pago a la lista 
            return pagoRealizado;
            
        }
        return null;
    }
    
    
    
    
}
