<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empleados | NóminaPro</title>
    <link rel="stylesheet" href="${ctx}/css/estilos.css">
</head>
<body>
<main class="contenedor">

    <header class="cabecera-pagina">
        <div>
            <span class="etiqueta">RECURSOS HUMANOS</span>
            <h1>Gestión de empleados</h1>
            <p>Registra y administra los datos de tu equipo.</p>
        </div>
        <a class="enlace-cabecera" href="${ctx}/app/inicio">← Volver al inicio</a>
    </header>
        <c:if test="${not empty error}">
            <div style="background:#fde8e8; color:#9b1c1c; border:1px solid #f5b5b5;
                        padding:12px 16px; border-radius:8px; margin-bottom:16px;">
                <c:out value="${error}"/>
            </div>
        </c:if>

    <section class="panel">
        <div class="titulo-panel">
            <div class="icono-pequeno">＋</div>
            <div>
                <h2>Registrar empleado</h2>
                <p class="texto-secundario">Introduce los datos del nuevo empleado.</p>
            </div>
        </div>

        <form action="${ctx}/app/empleados/insertar" method="post" class="formulario-grid">

            <div class="campo">
                <label for="dni">DNI</label>
                <input id="dni" type="text" name="dni" maxlength="9" required placeholder="12345678A">
            </div>

            <div class="campo">
                <label for="nombre">Nombre completo</label>
                <input id="nombre" type="text" name="nombre" maxlength="100" required placeholder="Nombre y apellidos">
            </div>

            <div class="campo">
                <label for="sexo">Sexo</label>
                <input id="sexo" type="text" name="sexo" maxlength="20" required placeholder="Introduce el valor">
            </div>

            <div class="campo">
                <label for="categoria">Categoría</label>
                <input id="categoria" type="number" name="categoria" min="1" required placeholder="Ej. 1">
            </div>

            <div class="campo">
                <label for="anyos">Años de antigüedad</label>
                <input id="anyos" type="number" name="anyos" min="0" required placeholder="Ej. 2">
            </div>

            <div class="campo campo-boton">
                <button type="submit" class="boton-primario">Guardar empleado</button>
            </div>
        </form>
    </section>

    <section class="panel">
        <div class="titulo-panel">
            <div class="icono-pequeno icono-azul">♧</div>
            <div>
                <h2>Listado de empleados</h2>
                <p class="texto-secundario">Consulta los empleados registrados en el sistema.</p>
            </div>
        </div>

        <c:choose>
            <c:when test="${empty empleados}">
                <div class="estado-vacio">
                    <div class="vacio-icono">👥</div>
                    <h3>No hay empleados registrados</h3>
                    <p>Utiliza el formulario para añadir el primer empleado.</p>
                </div>
            </c:when>

            <c:otherwise>
                <div class="tabla-contenedor">
                    <table>
                        <thead>
                        <tr>
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Sexo</th>
                            <th>Categoría</th>
                            <th>Antigüedad</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="e" items="${empleados}">
                            <tr>
                                <td><span class="dato-destacado"><c:out value="${e.dni}"/></span></td>
                                <td><c:out value="${e.nombre}"/></td>
                                <td><c:out value="${e.sexo}"/></td>
                                <td><span class="etiqueta-dato"><c:out value="${e.categoria}"/></span></td>
                                <td><c:out value="${e.anyos}"/> años</td>
                                <td>
                                    <form action="${ctx}/app/empleados/eliminar" method="post"
                                          class="form-accion"
                                          onsubmit="return confirm('¿Seguro que quieres eliminar este empleado?');">
                                        <input type="hidden" name="dni" value="<c:out value='${e.dni}'/>">
                                        <button type="submit" class="boton-eliminar">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </section>

    <footer class="pie">
        <a href="${ctx}/app/inicio">← Volver al panel principal</a>
    </footer>

</main>
</body>
</html>