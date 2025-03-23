
package com.mycompany.infraestructura.sistemaPago.implementaciones;

import java.time.LocalDate;
import java.util.Random;

/**
 * Clase que representa una tarjeta existente. 
 * @author victoria
 */
public class Tarjeta {
    private int id;
    private String numeroCuenta;
    private String propietario;
    private LocalDate fechaExpiracion;
    private int cvv;
    private Float saldo;

    public Tarjeta(String numeroCuenta, String propietario, LocalDate fechaExpiracion, int cvv, Float saldo) {
        Random random = new Random();
        this.id = random.nextInt(1000)+1; // genera el id de la tarjeta de manera aleatoria
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
        this.fechaExpiracion = fechaExpiracion;
        this.cvv = cvv;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getPropietario() {
        return propietario;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public int getCvv() {
        return cvv;
    }

    public Float getSaldo() {
        return saldo;
    }
    
    
}
