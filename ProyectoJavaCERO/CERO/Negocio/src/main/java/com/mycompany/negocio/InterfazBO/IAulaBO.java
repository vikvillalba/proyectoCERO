/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.negocio.InterfazBO;

import DTOs.GestionarClases.AulaClaseDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.AulaClase;
import Entidades.Clase;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public interface IAulaBO {
    
    public boolean validarDisponibilidadHorarioAula(NuevaClaseDTO nuevaClase , List<Clase> clasesPresencialesAula)throws NegocioException;
    public List<AulaClaseDTO> obtenerListaAulas();
    //metodo para obtener clases en aula 
    public void agregarClasePresencial(Clase clase)throws NegocioException;
    public AulaClaseDTO convertirAulaDTO(AulaClase aulaClase);
    public AulaClase buscarAulaClaseID(String idAulaClase) throws NegocioException;
    public AulaClase buscarAulaClaseObjectId(ObjectId id);
    
}
