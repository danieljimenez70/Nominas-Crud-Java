package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.dao.EmpleadoDAO;
import org.example.model.Empleado;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Empleado> empleados = empleadoDAO.listar();
            request.setAttribute("empleados", empleados);

            request.getRequestDispatcher("/empleados.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "Error al consultar los empleados", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        String dni = request.getParameter("dni");

        try {
            if ("insertar".equals(accion)) {

                Empleado empleado = new Empleado(
                        dni,
                        request.getParameter("nombre"),
                        request.getParameter("sexo"),
                        Integer.parseInt(request.getParameter("categoria")),
                        Integer.parseInt(request.getParameter("anyos"))
                );

                empleadoDAO.insertar(empleado);

            } else if ("actualizar".equals(accion)) {

                Empleado empleado = new Empleado(
                        dni,
                        request.getParameter("nombre"),
                        request.getParameter("sexo"),
                        Integer.parseInt(request.getParameter("categoria")),
                        Integer.parseInt(request.getParameter("anyos"))
                );

                empleadoDAO.actualizar(empleado);

            } else if ("eliminar".equals(accion)) {

                empleadoDAO.eliminar(dni);

            } else {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Acción no válida"
                );
                return;
            }

            response.sendRedirect(
                    request.getContextPath() + "/empleados");

        } catch (SQLException | NumberFormatException e) {
            throw new ServletException(
                    "No se pudo procesar la operación del empleado", e);
        }
    }
}