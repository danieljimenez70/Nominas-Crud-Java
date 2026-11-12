package org.example.command.empleado;

import org.example.command.Command;
import org.example.service.EmpleadoService;
import org.example.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EliminarEmpleadoCommand implements Command {

    private final EmpleadoService empleadoService = new EmpleadoService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            empleadoService.eliminar(request.getParameter("dni"));
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return "redirect:/app/empleados/listar";
    }
}