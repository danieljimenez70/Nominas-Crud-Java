package org.example.command.empleado;

import org.example.command.Command;
import org.example.service.EmpleadoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarEmpleadosCommand implements Command {

    private final EmpleadoService empleadoService = new EmpleadoService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("empleados", empleadoService.listar());
        return "empleados";
    }
}