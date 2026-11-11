
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Empleado" %>

<%
    List<Empleado> empleados =
            (List<Empleado>) request.getAttribute("empleados");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de empleados</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<main class="contenedor">
    <header>
        <h1>Gestión de empleados</h1>
        <p>Registra, consulta, modifica y elimina empleados.</p>
        <a href="index.jsp">Volver al inicio</a>
    </header>

    <section class="panel">
        <h2>Registrar empleado</h2>

        <form action="empleados" method="post">
            <input type="hidden" name="accion" value="insertar">

            <label for="dni">DNI</label>
            <input id="dni" type="text" name="dni" maxlength="9"
                   required placeholder="12345678A">

            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" name="nombre"
                   maxlength="100" required>

            <label for="sexo">Sexo</label>
            <input id="sexo" type="text" name="sexo"
                   maxlength="20" required placeholder="Masculino/Femenino">

            <label for="categoria">Categoría</label>
            <input id="categoria" type="number" name="categoria"
                   min="1" required>

            <label for="anyos">Años de antigüedad</label>
            <input id="anyos" type="number" name="anyos"
                   min="0" required>

            <button type="submit">Guardar empleado</button>
        </form>
    </section>

    <section class="panel">
        <h2>Listado de empleados</h2>

        <% if (empleados == null || empleados.isEmpty()) { %>
            <p>No hay empleados registrados.</p>
        <% } else { %>
            <div class="tabla-contenedor">
                <table>
                    <thead>
                    <tr>
                        <th>DNI</th>
                        <th>Nombre</th>
                        <th>Sexo</th>
                        <th>Categoría</th>
                        <th>Años</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Empleado e : empleados) { %>
                        <tr>
                            <td><%= e.getDni() %></td>
                            <td><%= e.getNombre() %></td>
                            <td><%= e.getSexo() %></td>
                            <td><%= e.getCategoria() %></td>
                            <td><%= e.getAnyos() %></td>
                            <td>
                                <form action="empleados" method="post">
                                    <input type="hidden" name="accion"
                                           value="eliminar">
                                    <input type="hidden" name="dni"
                                           value="<%= e.getDni() %>">
                                    <button type="submit"
                                            onclick="return confirm('¿Seguro que quieres eliminar este empleado?')">
                                        Eliminar
                                    </button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        <% } %>
    </section>
</main>
</body>
</html>
