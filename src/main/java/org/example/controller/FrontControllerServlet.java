package org.example.controller;

import org.example.command.Command;
import org.example.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/*")
public class FrontControllerServlet extends HttpServlet {

    private static final String PREFIJO_REDIRECT = "redirect:";
    private static final String CARPETA_VISTAS = "/WEB-INF/views/";

    private CommandFactory commandFactory;

    @Override
    public void init() {
        commandFactory = new CommandFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        procesar(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        procesar(req, resp);
    }

    private void procesar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        pasarMensajeDeSesionAPeticion(req);

        String ruta = req.getPathInfo();
        Command comando = (ruta == null) ? null : commandFactory.crear(ruta);

        if (comando == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Ruta no encontrada: " + ruta);
            return;
        }

        try {
            String resultado = comando.execute(req, resp);

            if (resultado.startsWith(PREFIJO_REDIRECT)) {
                resp.sendRedirect(req.getContextPath()
                        + resultado.substring(PREFIJO_REDIRECT.length()));
            } else {
                req.getRequestDispatcher(CARPETA_VISTAS + resultado + ".jsp")
                        .forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Error al ejecutar la acción " + ruta, e);
        }
    }

    /**
     * Mensaje "flash": el Command lo deja en la sesión antes de redirigir,
     * y aquí lo movemos a la petición para que la vista lo muestre una vez.
     */
    private void pasarMensajeDeSesionAPeticion(HttpServletRequest req) {
        HttpSession sesion = req.getSession(false);
        if (sesion != null && sesion.getAttribute("error") != null) {
            req.setAttribute("error", sesion.getAttribute("error"));
            sesion.removeAttribute("error");
        }
    }
}