package org.example.command.nomina;

import org.example.command.Command;
import org.example.dao.NominaDAO;
import org.example.model.Nomina;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class InsertarNominaCommand implements Command {

    private final NominaDAO nominaDAO = new NominaDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dni = request.getParameter("dni");
        BigDecimal sueldo = new BigDecimal(request.getParameter("sueldo"));

        nominaDAO.insertar(new Nomina(dni, sueldo));
        return "redirect:/app/nominas/listar";
    }
}