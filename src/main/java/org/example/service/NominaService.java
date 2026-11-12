package org.example.service;

import org.example.dao.EmpleadoDAO;
import org.example.dao.NominaDAO;
import org.example.model.Nomina;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * Patrón Service Layer: reglas de negocio de las nóminas.
 */
public class NominaService {

    private final NominaDAO nominaDAO = new NominaDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public List<Nomina> listar() throws ServiceException {
        try {
            return nominaDAO.listar();
        } catch (SQLException e) {
            throw new ServiceException("No se pudo consultar la lista de nóminas", e);
        }
    }

    public void insertar(Nomina nomina) throws ServiceException {
        validar(nomina);
        try {
            if (empleadoDAO.buscarPorDni(nomina.getDni()) == null) {
                throw new ServiceException("No existe ningún empleado con el DNI " + nomina.getDni());
            }
            if (nominaDAO.buscarPorDni(nomina.getDni()) != null) {
                throw new ServiceException("El empleado " + nomina.getDni() + " ya tiene una nómina");
            }
            nominaDAO.insertar(nomina);
        } catch (SQLException e) {
            throw new ServiceException("No se pudo guardar la nómina", e);
        }
    }

    public void actualizar(Nomina nomina) throws ServiceException {
        validar(nomina);
        try {
            if (!nominaDAO.actualizar(nomina)) {
                throw new ServiceException("No existe ninguna nómina para el DNI " + nomina.getDni());
            }
        } catch (SQLException e) {
            throw new ServiceException("No se pudo actualizar la nómina", e);
        }
    }

    public void eliminar(String dni) throws ServiceException {
        try {
            if (!nominaDAO.eliminar(dni)) {
                throw new ServiceException("No existe ninguna nómina para el DNI " + dni);
            }
        } catch (SQLException e) {
            throw new ServiceException("No se pudo eliminar la nómina", e);
        }
    }

    private void validar(Nomina nomina) throws ServiceException {
        if (nomina.getDni() == null || nomina.getDni().trim().isEmpty()) {
            throw new ServiceException("El DNI es obligatorio");
        }
        if (nomina.getSueldo() == null || nomina.getSueldo().compareTo(BigDecimal.ZERO) < 0) {
            throw new ServiceException("El sueldo no puede ser negativo");
        }
    }
}