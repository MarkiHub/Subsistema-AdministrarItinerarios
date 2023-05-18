/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.fachada;

import ObjNegocio.Especie;
import ObjNegocio.Guia;
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
     * @throws PersistenciaException Si existe el itinerario
     */
    public Itinerario registrarItinerario(Itinerario iti) throws PersistenciaException;

    /**
     * Recupera los itinerarios del guia que coincidan con el id dado
     *
     * @param identificadorId id a buscar
     * @return Lista de itinerarios del guia
     * @throws PersistenciaException Si no existen itinerarios
     */
    public List<Itinerario> recuperarItinerarios(String identificadorId) throws PersistenciaException;

    /**
     * Recupera los datos necesarios para llenar el formulario de registro de
     * itinerarios
     *
     * @return Lista de datos (especies)
     * @throws PersistenciaException Si no existen datos para recuperar
     */
    public List<Especie> recuperarDatosFormulario() throws PersistenciaException;

    /**
     * Inserta la infomracion requierida para el caso de uso
     *
     * @throws org.itson.fachada.excepciones.PersistenciaException Si los datos
     * ya han sido insertados
     */
    public void insertarDummies() throws PersistenciaException;

    /**
     * Recupera los itinerarios que coincidan con el nombre dado
     *
     * @param nombre Nombre del itinerario
     * @return Itinerario encontrado
     * @throws PersistenciaException Si no encontro el itinerario
     */
    public Itinerario recuperarItinerariosPorNombre(String nombre) throws PersistenciaException;

    /**
     * Recupera todos los guias contenidos en la base de datos
     *
     * @return Lista de guias
     * @throws PersistenciaException Si no existen guias almacenados en la base
     * de datos
     */
    public List<Guia> recuperarGuias() throws PersistenciaException;

    /**
     * Busca un itinerario que tenga un nombre similar que el que fue dado en el
     * parametro
     *
     * @param nombre Nombre similar
     * @return Lista de itinerarios
     * @throws PersistenciaException Si no existen itinerarios con ese nombre
     */
    public List<Itinerario> consultarItinerariosNombreSimilar(String nombre) throws PersistenciaException;
}
