/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ObserverInscribirClase;

import Entidades.Alumno;
import Entidades.Clase;

/**
 *
 * @author Jack Murrieta
 */
public interface INotificadorInscripcion {
    void notificarRegistroInscripcion(Alumno alumno, Clase clase);
    void notificarCancelacionInscripcion(Alumno alumno, Clase clase);
}
