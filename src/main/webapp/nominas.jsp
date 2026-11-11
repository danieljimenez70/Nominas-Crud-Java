<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Nomina" %>
<%
List<Nomina> nominas = (List<Nomina>) request.getAttribute("nominas");
%>

<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de nóminas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<main class="contenedor">
    <header>
        <h1>Gestión de nóminas</h1>
        <p>Consulta, registra, modifica y elimina nóminas.</p>
    </header>

<section class="panel">
    <h2>Registrar nómina</h2>
    <form action="${pageContext.request.contextPath}/nominas" method="post">
        <input type="hidden" name="accion" value="insertar">

        <label for="dni">DNI del empleado</label>
        <input type="text" id="dni" name="dni" maxlength="9" required>

        <label for="sueldo">Sueldo (€)</label>
        <input type="number" id="sueldo" name="sueldo"
               min="0" step="0.01" required>

        <button type="submit">Registrar nómina</button>
    </form>
</section>

<section class="panel">
    <h2>Nóminas registradas</h2>
    <table>
        <thead>
        <tr>
            <th>DNI</th>
            <th>Sueldo</th>
            <th>Modificar</th>
            <th>Eliminar</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (nominas != null && !nominas.isEmpty()) {
                for (Nomina nomina : nominas) {
        %>
        <tr>
            <td><%= nomina.getDni() %></td>
            <td><%= nomina.getSueldo() %> €</td>
            <td>
                <form action="${pageContext.request.contextPath}/nominas" method="post">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="dni" value="<%= nomina.getDni() %>">
                    <input type="number" name="sueldo"
                           min="0" step="0.01"
                           value="<%= nomina.getSueldo() %>" required>
                    <button type="submit">Guardar</button>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/nominas" method="post"
                      onsubmit="return confirm('¿Seguro que quieres eliminar esta nómina?');">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="dni" value="<%= nomina.getDni() %>">
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">No hay nóminas registradas.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <p><a href="${pageContext.request.contextPath}/">Volver al inicio</a></p>
</section>

</main>
</body>
</html>