package org.example.controller;

import org.example.command.Command;
import org.example.command.InicioCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/app/*")
public class FrontControllerServlet extends HttpServlet {

    private static final String PREFIJO_REDIRECT = "redirect:";
    private static final String CARPETA_VISTAS = "/WEB-INF/views/";

    // Provisional: en el paso 4 esto pasa a una CommandFactory
    private final Map<String, Command> comandos = new HashMap<>();

    @Override
    public void init() {
        comandos.put("/inicio", new InicioCommand());
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

        String ruta = req.getPathInfo();
        Command comando = (ruta == null) ? null : comandos.get(ruta);

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
}