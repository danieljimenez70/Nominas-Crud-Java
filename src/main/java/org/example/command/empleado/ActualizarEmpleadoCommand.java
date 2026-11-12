package org.example.command.empleado;

import org.example.command.Command;
import org.example.dao.EmpleadoDAO;
import org.example.model.Empleado;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActualizarEmpleadoCommand implements Command {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Empleado empleado = new Empleado(
                request.getParameter("dni"),
                request.getParameter("nombre"),
                request.getParameter("sexo"),
                Integer.parseInt(request.getParameter("categoria")),
                Integer.parseInt(request.getParameter("anyos")));

        empleadoDAO.actualizar(empleado);
        return "redirect:/app/empleados/listar";
    }
}