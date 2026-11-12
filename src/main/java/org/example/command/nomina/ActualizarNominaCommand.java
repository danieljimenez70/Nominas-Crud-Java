package org.example.command.nomina;

import org.example.command.Command;
import org.example.model.Nomina;
import org.example.service.NominaService;
import org.example.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class ActualizarNominaCommand implements Command {

    private final NominaService nominaService = new NominaService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String dni = request.getParameter("dni");
            BigDecimal sueldo = new BigDecimal(request.getParameter("sueldo"));

            nominaService.actualizar(new Nomina(dni, sueldo));
        } catch (ServiceException e) {
            request.getSession().setAttribute("error", e.getMessage());
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "El sueldo introducido no es válido");
        }
        return "redirect:/app/nominas/listar";
    }
}