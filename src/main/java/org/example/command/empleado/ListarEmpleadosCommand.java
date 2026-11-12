package org.example.command.empleado;

import org.example.command.Command;
import org.example.dao.EmpleadoDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarEmpleadosCommand implements Command {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("empleados", empleadoDAO.listar());
        return "empleados";
    }
}