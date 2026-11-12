package org.example.command.nomina;

import org.example.command.Command;
import org.example.service.NominaService;
import org.example.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EliminarNominaCommand implements Command {

    private final NominaService nominaService = new NominaService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            nominaService.eliminar(request.getParameter("dni"));
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        }
        return "redirect:/app/nominas/listar";
    }
}