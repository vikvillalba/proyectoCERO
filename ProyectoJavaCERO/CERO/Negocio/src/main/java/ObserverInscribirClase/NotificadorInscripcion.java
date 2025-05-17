/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObserverInscribirClase;

import Entidades.Alumno;
import Entidades.Clase;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public class NotificadorInscripcion implements INotificadorInscripcion {

    private List<INotificadorInscripcion> listeners = new ArrayList<>();

    public void agregarListener(INotificadorInscripcion listener) {
        listeners.add(listener);
    }

    public void notificarInscripcion(Alumno alumno, Clase clase) {
        for (INotificadorInscripcion listener : listeners) {
            listener.notificarInscripcion(alumno, clase);
        }
    }
}
