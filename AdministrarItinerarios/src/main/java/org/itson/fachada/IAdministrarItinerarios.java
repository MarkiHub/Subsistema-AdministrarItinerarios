/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.fachada;

import ObjNegocio.Especie;
import ObjNegocio.Itinerario;
import java.util.List;
import org.itson.fachada.excepciones.PersistenciaException;

/**
 * Interfaz de acceso al subsistema de administrar itinerarios
 *
 * @author Elkur
 */
public interface IAdministrarItinerarios {

    /**
     * registra el itinerario dato en el parametro
     *
     * @param iti itinerario a registrar
     * @return Itinerario registrado
     */
    /**
     * registra el itinerario dato en el parametro
     *
     * @param iti itinerario a registrar
     * @return Itinerario registrado
     * @throws PersistenciaException Si existe el itinerario
     */
    public Itinerario registrarItinerario(Itinerario iti) throws PersistenciaException;

    /**
     * Recupera los itinerarios del guia que coincidan con el id dado
     *
     * @param identificadorId id a buscar
     * @return Lista de itinerarios del guia
     */
    public List<Itinerario> recuperarItinerarios(String identificadorId) throws PersistenciaException;

    /**
     * Recupera los datos necesarios para llenar el formulario de registro de
     * itinerarios
     *
     * @return Lista de datos (especies)
     */
    public List<Especie> recuperarDatosFormulario() throws PersistenciaException;
    
    /**
     * Inserta la infomracion requierida para el caso de uso
     */
    public void insertarDummies()throws PersistenciaException;
}
