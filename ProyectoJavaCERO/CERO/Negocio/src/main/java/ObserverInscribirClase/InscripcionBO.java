/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObserverInscribirClase;

import Entidades.Alumno;
import Entidades.Clase;
import implementaciones.InscripcionesDAO;

/**
 *
 * @author Jack Murrieta
 */
public class InscripcionBO {

    private InscripcionesDAO inscripcionesDAO;
    private NotificadorInscripcion notificadorInscripcion;

    public InscripcionBO(InscripcionesDAO inscripcionesDAO, NotificadorInscripcion notificador) {
        this.inscripcionesDAO = inscripcionesDAO;
        this.notificadorInscripcion = notificador;
    }

    public void realizarInscripcion(Alumno alumno, Clase clase) {
        //inscripcionesDAO.registrarInscripcion(nscripcion inscripcion);
        //realizar la inscripcion
        notificadorInscripcion.notificarInscripcion(alumno, clase);
    }

}
