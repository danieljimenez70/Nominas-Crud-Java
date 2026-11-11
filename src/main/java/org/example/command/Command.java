package org.example.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    /**
     * @return nombre de la vista (sin .jsp) o "redirect:/ruta" para redirigir
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}