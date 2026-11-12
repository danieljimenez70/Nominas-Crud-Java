package org.example.command.nomina;

import org.example.command.Command;
import org.example.service.NominaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarNominasCommand implements Command {

    private final NominaService nominaService = new NominaService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("nominas", nominaService.listar());
        return "nominas";
    }
}