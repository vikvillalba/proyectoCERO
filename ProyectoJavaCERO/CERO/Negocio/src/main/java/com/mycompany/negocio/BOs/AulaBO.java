/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocio.BOs;

import DTOs.GestionarClases.NuevaClaseDTO;
import Entidades.Clase;
import com.mycompany.negocio.InterfazBO.IAulaBO;
import java.util.List;

/**
 *
 * @author Jack Murrieta
 */
public class AulaBO implements IAulaBO{

    @Override
    public boolean validarDisponibilidadHorarioAula(NuevaClaseDTO nuevaClase, List<Clase> clasesPresencialesAula) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Clase> obtenerListaAulas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
