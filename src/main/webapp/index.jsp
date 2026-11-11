<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Nóminas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <main class="contenedor">
        <header>
            <h1>Gestión de Nóminas</h1>
            <p>Aplicación de gestión de empleados y nóminas</p>
        </header>

        <section class="panel">
            <h2>Gestión de empleados</h2>
            <p>Registra, consulta, modifica y elimina empleados.</p>
            <a href="${pageContext.request.contextPath}/empleados">
                <button type="button">Gestionar empleados</button>
            </a>
        </section>

        <section class="panel">
            <h2>Gestión de nóminas</h2>
            <p>Consulta y calcula las nóminas de los empleados.</p>
            <a href="${pageContext.request.contextPath}/nominas">
                <button type="button">Gestionar nóminas</button>
            </a>
        </section>
    </main>
</body>
</html>
