package org.example.command;

import org.example.command.empleado.ActualizarEmpleadoCommand;
import org.example.command.empleado.EliminarEmpleadoCommand;
import org.example.command.empleado.InsertarEmpleadoCommand;
import org.example.command.empleado.ListarEmpleadosCommand;
import org.example.command.nomina.ActualizarNominaCommand;
import org.example.command.nomina.EliminarNominaCommand;
import org.example.command.nomina.InsertarNominaCommand;
import org.example.command.nomina.ListarNominasCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Patrón Factory: decide qué Command corresponde a cada ruta.
 * El Front Controller solo pide "dame el comando de esta ruta"
 * y no conoce ninguna clase concreta.
 */
public class CommandFactory {

    private final Map<String, Command> comandos = new HashMap<>();

    public CommandFactory() {
        comandos.put("/inicio", new InicioCommand());

        comandos.put("/empleados/listar", new ListarEmpleadosCommand());
        comandos.put("/empleados/insertar", new InsertarEmpleadoCommand());
        comandos.put("/empleados/actualizar", new ActualizarEmpleadoCommand());
        comandos.put("/empleados/eliminar", new EliminarEmpleadoCommand());

        comandos.put("/nominas/listar", new ListarNominasCommand());
        comandos.put("/nominas/insertar", new InsertarNominaCommand());
        comandos.put("/nominas/actualizar", new ActualizarNominaCommand());
        comandos.put("/nominas/eliminar", new EliminarNominaCommand());
    }

    /**
     * @return el Command de la ruta, o null si la ruta no existe
     */
    public Command crear(String ruta) {
        return comandos.get(ruta);
    }
}