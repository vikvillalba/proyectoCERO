/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.negocio.InterfazBO;

import DTOs.GestionarClases.MaestroDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.Clase;
import Entidades.Maestro;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jack Murrieta
 */
public interface IMaestroBO {

    public boolean validarDisponibilidadHorarioMaestro(NuevaClaseDTO nuevaClase, List<Clase> clasesImpartidas) throws NegocioException;

    public List<MaestroDTO> obtenerListaMaestros();

    public MaestroDTO convertirMaestroDTO(Maestro maestro);

    public Maestro buscarMaestroID(String idMaestro) throws NegocioException;

    public Maestro buscarMaestroObjectId(ObjectId id);
}
