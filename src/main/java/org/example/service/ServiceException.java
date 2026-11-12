package org.example.service;

/**
 * Error de negocio con un mensaje que se puede mostrar al usuario
 * (por ejemplo "Ya existe un empleado con ese DNI").
 */
public class ServiceException extends Exception {

    public ServiceException(String mensaje) {
        super(mensaje);
    }

    public ServiceException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}