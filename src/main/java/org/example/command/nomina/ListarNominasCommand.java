package org.example.command.nomina;

import org.example.command.Command;
import org.example.dao.NominaDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarNominasCommand implements Command {

    private final NominaDAO nominaDAO = new NominaDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("nominas", nominaDAO.listar());
        return "nominas";
    }
}