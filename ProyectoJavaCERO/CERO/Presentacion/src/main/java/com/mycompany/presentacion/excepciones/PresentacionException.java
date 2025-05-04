
package com.mycompany.presentacion.excepciones;

/**
 *
 * @author victoria
 */
public class PresentacionException extends Exception{

    public PresentacionException() {
    }

    public PresentacionException(String message) {
        super(message);
    }

    public PresentacionException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
