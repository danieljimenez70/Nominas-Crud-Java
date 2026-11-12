package org.example.service;

import org.example.dao.EmpleadoDAO;
import org.example.model.Empleado;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Patrón Service Layer: aquí viven las reglas de negocio de los empleados.
 * Los Commands usan este servicio y nunca llaman directamente al DAO.
 */
public class EmpleadoService {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public List<Empleado> listar() throws ServiceException {
        try {
            return empleadoDAO.listar();
        } catch (SQLException e) {
            throw new ServiceException("No se pudo consultar la lista de empleados", e);
        }
    }

    public void insertar(Empleado empleado) throws ServiceException {
        validar(empleado);
        try {
            if (empleadoDAO.buscarPorDni(empleado.getDni()) != null) {
                throw new ServiceException("Ya existe un empleado con el DNI " + empleado.getDni());
            }
            empleadoDAO.insertar(empleado);
        } catch (SQLException e) {
            throw new ServiceException("No se pudo guardar el empleado", e);
        }
    }

    public void actualizar(Empleado empleado) throws ServiceException {
        validar(empleado);
        try {
            if (!empleadoDAO.actualizar(empleado)) {
                throw new ServiceException("No existe ningún empleado con el DNI " + empleado.getDni());
            }
        } catch (SQLException e) {
            throw new ServiceException("No se pudo actualizar el empleado", e);
        }
    }

    public void eliminar(String dni) throws ServiceException {
        try {
            if (!empleadoDAO.eliminar(dni)) {
                throw new ServiceException("No existe ningún empleado con el DNI " + dni);
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new ServiceException(
                    "No se puede eliminar el empleado " + dni + " porque tiene una nómina asociada", e);
        } catch (SQLException e) {
            throw new ServiceException("No se pudo eliminar el empleado", e);
        }
    }

    private void validar(Empleado empleado) throws ServiceException {
        if (empleado.getDni() == null || empleado.getDni().trim().isEmpty()) {
            throw new ServiceException("El DNI es obligatorio");
        }
        if (empleado.getDni().trim().length() > 9) {
            throw new ServiceException("El DNI no puede tener más de 9 caracteres");
        }
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new ServiceException("El nombre es obligatorio");
        }
        if (empleado.getCategoria() < 1) {
            throw new ServiceException("La categoría debe ser 1 o superior");
        }
        if (empleado.getAnyos() < 0) {
            throw new ServiceException("Los años de antigüedad no pueden ser negativos");
        }
    }
}