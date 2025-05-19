/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GestionarClasesPersistencia;

import Entidades.AulaClase;
import Entidades.Clase;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author Jack Murrrieta
 */
public interface IAulasClaseDAO {
    public List<Clase> obtenerAulaClases(AulaClase aula);
    public void agregarClasePresencial(Clase clase)throws PersistenciaException;
    //buscar espacio Horario Metodo
    public List<AulaClase> obtenerAulas();
    public AulaClase buscarClase(String aula)throws PersistenciaException;
}
