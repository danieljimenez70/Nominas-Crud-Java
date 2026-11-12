package org.example.command.empleado;

import org.example.command.Command;
import org.example.model.Empleado;
import org.example.service.EmpleadoService;
import org.example.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertarEmpleadoCommand implements Command {

    private final EmpleadoService empleadoService = new EmpleadoService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Empleado empleado = new Empleado(
                    request.getParameter("dni"),
                    request.getParameter("nombre"),
                    request.getParameter("sexo"),
                    Integer.parseInt(request.getParameter("categoria")),
                    Integer.parseInt(request.getParameter("anyos")));

            empleadoService.insertar(empleado);
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return "redirect:/app/empleados/listar";
    }
}