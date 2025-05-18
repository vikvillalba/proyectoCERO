/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObserverInscribirClase;

import DAOs.IClasesDAO;
import Entidades.Alumno;
import Entidades.Clase;

/**
 *
 * @author Jack Murrieta
 */
public class ClaseBO implements INotificadorInscripcion {

    private IClasesDAO clasesDAO;

    public ClaseBO(IClasesDAO clasesDAO) {
        this.clasesDAO = clasesDAO;
    }

    @Override
    public void notificarInscripcion(Alumno alumno, Clase clase) {
        // Lógica al recibir notificación de inscripción
        System.out.println("ClaseBO: Alumno inscrito: " + alumno.getNombre() + " en clase: " + clase.getNombre());
        actualizarCupo(clase);
    }

    public void actualizarCupo(Clase clase) {
//        // Ejemplo de lógica para reducir cupo
//        clase.reducirCupo();
//        clasesDAO.actualizar(clase);
    }

    public int contarInscripciones() {
//        return clasesDAO.contar();
        return 0;
    }
}
