package org.example.controller;

import org.example.dao.NominaDAO;
import org.example.model.Nomina;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/nominas")
public class NominaServlet extends HttpServlet {

    private final NominaDAO nominaDAO = new NominaDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Nomina> nominas = nominaDAO.listar();
            request.setAttribute("nominas", nominas);

            request.getRequestDispatcher("/nominas.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                    "Error al consultar las nóminas", e);
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

                BigDecimal sueldo = new BigDecimal(
                        request.getParameter("sueldo"));

                Nomina nomina = new Nomina(dni, sueldo);
                nominaDAO.insertar(nomina);

            } else if ("actualizar".equals(accion)) {

                BigDecimal sueldo = new BigDecimal(
                        request.getParameter("sueldo"));

                Nomina nomina = new Nomina(dni, sueldo);
                nominaDAO.actualizar(nomina);

            } else if ("eliminar".equals(accion)) {

                nominaDAO.eliminar(dni);

            } else {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Acción no válida");
                return;
            }

            response.sendRedirect(
                    request.getContextPath() + "/nominas");

        } catch (SQLException | NumberFormatException e) {
            throw new ServletException(
                    "No se pudo procesar la operación de la nómina", e);
        }
    }
}