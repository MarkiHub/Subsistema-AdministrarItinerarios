/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.fachada.excepciones;

/**
 * Clase de excepcion que indica anomalias en el lado de persistencia
 *
 * @author Elkur
 */
public class PersistenciaException extends Exception {

    /**
     * Constructor que recibe el mensaje de excepcion
     *
     * @param message Mensaje de excepcion
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Regresa el mensaje
     *
     * @return mensaje
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
