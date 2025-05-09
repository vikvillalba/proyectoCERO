package com.mycompany.negocio.excepciones;

/**
 *
 * @author victoria
 */
public class NegocioException extends Exception{

    public NegocioException() {
    }

    public NegocioException(String message) {
        super(message);
    }
    
}
