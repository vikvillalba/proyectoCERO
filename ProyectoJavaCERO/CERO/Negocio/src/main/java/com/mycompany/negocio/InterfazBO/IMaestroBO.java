/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.negocio.InterfazBO;

import DTOs.GestionarClases.MaestroDTO;
import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.Clase;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public interface IMaestroBO {
    public boolean validarDisponibilidadHorarioMaestro(NuevaClaseDTO nuevaClase,List<Clase> clasesImpartidad);
    public List<MaestroDTO> obtenerListaMaestros();
    
    
}
