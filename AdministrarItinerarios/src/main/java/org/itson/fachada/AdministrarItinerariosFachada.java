/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.fachada;

import ObjNegocio.Especie;
import ObjNegocio.Itinerario;
import ObjNegocio.Zona;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.fachada.excepciones.PersistenciaException;
import org.itson.persistencia.excepciones.DAOException;
import org.itson.persistencia.implementacion.EspeciesDAO;
import org.itson.persistencia.implementacion.InsertarDummies;
import org.itson.persistencia.implementacion.ItinerariosDAO;
import org.itson.persistencia.interfaces.IEspeciesDAO;
import org.itson.persistencia.interfaces.IItinerariosDAO;

/**
 * Subsistema para la administracion de los itinerarios del zoologico
 *
 * @author Elkur
 */
public class AdministrarItinerariosFachada implements IAdministrarItinerarios {

    /**
     * Clase interna que realiza las operaciones
     */
    private AdministrarItinerarios adm;

    /**
     * Constructor que recibe el nombre de la base de datos a utilizar
     *
     * @param BASE_DATOS Nombre de la base
     * @throws PersistenciaException Si no se pudo establecer conexion con la
     * base especificada
     */
    public AdministrarItinerariosFachada(String BASE_DATOS) throws PersistenciaException {
        this.adm = new AdministrarItinerarios(BASE_DATOS);
    }

    /**
     * Registra el itinerario dado en el parametro
     *
     * @param iti Itinerario a registrar
     * @return Itinerario registrado
     * @throws PersistenciaException Si no se pudo persistir el itinerario
     */
    @Override
    public Itinerario registrarItinerario(Itinerario iti) throws PersistenciaException {
        return adm.registrarItinerario(iti);
    }

    /**
     * Recupera los itinerarios del guia al que pertenece el id
     *
     * @param identificadorId id del guia
     * @return Lista de itinerarios del guia
     * @throws PersistenciaException Si no se pudo realizar la operacion o si
     * los parametros de entrada no son validos
     */
    @Override
    public List<Itinerario> recuperarItinerarios(String identificadorId) throws PersistenciaException {
        return adm.recuperarItinerarios(identificadorId);
    }

    /**
     * Recupera los datos necesarios para llenar el formulario de itinerario
     *
     * @return Lista de especies
     * @throws PersistenciaException Si no existen datos de especies en la base
     *
     */
    @Override
    public List<Especie> recuperarDatosFormulario() throws PersistenciaException {
        return adm.recuperarDatos();
    }

    /**
     * Inserta la infomracion requierida para el caso de uso
     */
    @Override
    public void insertarDummies() throws PersistenciaException{
        adm.insertarDummies();
    }

    /**
     * Clase que realiza las operaciones de administracion
     */
    protected class AdministrarItinerarios {

        /**
         * Nombre de la base de datos
         */
        private final String BASE_DATOS;
        /**
         * Acceso a datos de itinerarios
         */
        private IItinerariosDAO itiDao;
        /**
         * Acceso a datos de especies
         */
        private IEspeciesDAO espDao;

        private InsertarDummies inDum;

        /**
         * Constructor que inicializa el acceso a datos
         *
         * @param BASE_DATOS Nombre de la base de datos
         * @throws PersistenciaException Si no se pudo realizar la conexion con
         * la base de datos
         */
        public AdministrarItinerarios(String BASE_DATOS) throws PersistenciaException {
            this.BASE_DATOS = BASE_DATOS;
            try {

                this.itiDao = new ItinerariosDAO(BASE_DATOS);
                this.espDao = new EspeciesDAO(BASE_DATOS);
                this.inDum = new InsertarDummies(BASE_DATOS);

            } catch (DAOException ex) {
                throw new PersistenciaException("No se pudo iniciar el programa");
            }
        }

        /**
         * Registra el itinerario dado en el parametro
         *
         * @param iti Itinerario a insertar
         * @return Itinerario insertado
         * @throws PersistenciaException Si el itinerario existe o si no se pudo
         * realizar la operacion
         */
        public Itinerario registrarItinerario(Itinerario iti) throws PersistenciaException {
            Itinerario itiExiste = itiDao.consultarNombre(iti.getNombre());
            if (itiExiste != null) {
                throw new PersistenciaException("Itinerario existente");
            }

            try {
                iti = itiDao.insertar(iti);
            } catch (DAOException ex) {
                throw new PersistenciaException("No se pudo agregar el itinerario");
            }

            return iti;
        }

        /**
         * Recupera los itinerarios del guia en base a su id
         *
         * @param id id del guia
         * @return Lista de itinerarios
         * @throws PersistenciaException Si el id esta vacio o es null
         */
        public List<Itinerario> recuperarItinerarios(String id) throws PersistenciaException {
            if (id == null) {
                throw new PersistenciaException("id null");
            }
            if (id.isBlank()) {
                throw new PersistenciaException("No se puede buscar un id vacio");
            }
            return itiDao.consultar(id);
        }

        /**
         * Recupera los datos de las especies para llenar el formulario de
         * itinerarios
         *
         * @return Lista de especies
         * @throws PersistenciaException Si no existen datos de especies en la
         * base de datos
         */
        public List<Especie> recuperarDatos() throws PersistenciaException {
            List<Especie> datos = espDao.consultar();
            if (datos.isEmpty()) {
                throw new PersistenciaException("No hay datos para desplegar");
            }
            return datos;
        }

        /**
         * Inserta la infomracion requierida para el caso de uso
         */
        public void insertarDummies() throws PersistenciaException {
            try {
                inDum.insertarDummies();
            }catch(DAOException e){
                throw new PersistenciaException(e.getMessage());
            }
        }

        public HashSet<Zona> obtenerZonas(List<Especie> esp) {
            HashSet<Zona> zonasRecorridas = new HashSet<>();
            for (Especie e : esp) {
                zonasRecorridas.add(e.getZona());
            }
            return zonasRecorridas;
        }

        public float CalcularLongitud(HashSet<Zona> zonasRecorridas) {
            float longitud = 0;
            for (Zona e : zonasRecorridas) {
                longitud += e.getExtension();
            }

            return longitud;
        }

        public int calcularMaxVisitantes(HashSet<Zona> zonasRecorridas) {
            float minimoVisitantes = ((Zona) zonasRecorridas.toArray()[0]).getExtension();
            for (Zona e : zonasRecorridas) {
                if (e.getExtension() < minimoVisitantes) {
                    minimoVisitantes = e.getExtension();
                }
            }
            return (int) (minimoVisitantes / 50.0f);
        }

        public int calcularNumEspecies(List<Especie> esp) {
            return esp.size();
        }
    }
}
