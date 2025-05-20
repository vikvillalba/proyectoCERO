/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocio.BOs;

import DTOs.GestionarClases.AulaClaseDTO;
import Entidades.AulaClase;
import Entidades.Clase;
import Excepciones.PersistenciaException;
import com.mycompany.negocio.InterfazBO.IAulaBO;
import com.mycompany.negocio.excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import GestionarClasesPersistencia.IAulasClaseDAO;

/**
 *
 * @author Jack Murrieta
 */
public class AulaBO implements IAulaBO {

    private IAulasClaseDAO aulaDAO;

    public AulaBO(IAulasClaseDAO aulaDAO) {
        this.aulaDAO = aulaDAO;
    }


    @Override
    public List<AulaClaseDTO> obtenerListaAulas() {
        List<AulaClaseDTO> aulasDTO = new ArrayList<>();
        List<AulaClase> aulas = aulaDAO.obtenerAulas();
        for (AulaClase aula : aulas) {
            AulaClaseDTO maestroDTO = convertirAulaDTO(aula);
            aulasDTO.add(maestroDTO);
        }
        return aulasDTO;

    }

    @Override
    public void agregarClasePresencial(Clase clase) throws NegocioException {
        try {
            aulaDAO.agregarClasePresencial(clase);
        } catch (PersistenciaException ex) {
            throw new NegocioException("el Id de la clase no es valido");
        }
    }

    @Override
    public AulaClaseDTO convertirAulaDTO(AulaClase aulaClase) {
        String idAula = aulaClase.getIdString();
        AulaClaseDTO aulaDTO = new AulaClaseDTO(idAula, aulaClase.getNombreAula());
        return aulaDTO;
    }

    @Override
    public AulaClase buscarAulaClaseID(String idAulaClase) throws NegocioException {
      try {
            return aulaDAO.buscarClase(idAulaClase);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @Override
    public List<Clase> obtenerClasesPresencialesAula(String idAula) {
        List<Clase> clasesPresencialesAula = aulaDAO.obtenerAulaClases(idAula);
        return clasesPresencialesAula;
    }

}
