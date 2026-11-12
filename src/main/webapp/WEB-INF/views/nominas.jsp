<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<fmt:setLocale value="es_ES"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nóminas | NóminaPro</title>
    <link rel="stylesheet" href="${ctx}/css/estilos.css">
</head>
<body>
<main class="contenedor">

    <header class="cabecera-pagina">
        <div>
            <span class="etiqueta">ADMINISTRACIÓN SALARIAL</span>
            <h1>Gestión de nóminas</h1>
            <p>Registra, consulta y actualiza los salarios de los empleados.</p>
        </div>
        <a class="enlace-cabecera" href="${ctx}/app/inicio">← Volver al inicio</a>
    </header>

    <section class="panel">
        <div class="titulo-panel">
            <div class="icono-pequeno icono-verde">€</div>
            <div>
                <h2>Registrar nómina</h2>
                <p class="texto-secundario">Introduce el DNI y el sueldo del empleado.</p>
            </div>
        </div>

        <form action="${ctx}/app/nominas/insertar" method="post"
              class="formulario-grid formulario-nomina">

            <div class="campo">
                <label for="dni">DNI del empleado</label>
                <input type="text" id="dni" name="dni" maxlength="9" required placeholder="12345678A">
            </div>

            <div class="campo">
                <label for="sueldo">Sueldo mensual (€)</label>
                <input type="number" id="sueldo" name="sueldo" min="0" step="0.01" required placeholder="Ej. 1800.00">
            </div>

            <div class="campo campo-boton">
                <button type="submit" class="boton-verde">Registrar nómina</button>
            </div>
        </form>
    </section>

    <section class="panel">
        <div class="titulo-panel">
            <div class="icono-pequeno icono-azul">▤</div>
            <div>
                <h2>Nóminas registradas</h2>
                <p class="texto-secundario">Consulta los salarios y actualiza los importes.</p>
            </div>
        </div>

        <c:choose>
            <c:when test="${empty nominas}">
                <div class="estado-vacio">
                    <div class="vacio-icono">💶</div>
                    <h3>No hay nóminas registradas</h3>
                    <p>Registra una nómina utilizando el formulario superior.</p>
                </div>
            </c:when>

            <c:otherwise>
                <div class="tabla-contenedor">
                    <table>
                        <thead>
                        <tr>
                            <th>DNI del empleado</th>
                            <th>Sueldo actual</th>
                            <th>Actualizar sueldo</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="n" items="${nominas}">
                            <tr>
                                <td><span class="dato-destacado"><c:out value="${n.dni}"/></span></td>

                                <td>
                                    <span class="sueldo">
                                        <fmt:formatNumber value="${n.sueldo}"
                                                          minFractionDigits="2"
                                                          maxFractionDigits="2"/> €
                                    </span>
                                </td>

                                <td>
                                    <form action="${ctx}/app/nominas/actualizar" method="post"
                                          class="form-actualizar">
                                        <input type="hidden" name="dni" value="<c:out value='${n.dni}'/>">
                                        <input type="number" name="sueldo"
                                               aria-label="Nuevo sueldo para <c:out value='${n.dni}'/>"
                                               min="0" step="0.01"
                                               value="<c:out value='${n.sueldo}'/>" required>
                                        <button type="submit" class="boton-guardar">Guardar</button>
                                    </form>
                                </td>

                                <td>
                                    <form action="${ctx}/app/nominas/eliminar" method="post"
                                          class="form-accion"
                                          onsubmit="return confirm('¿Seguro que quieres eliminar esta nómina?');">
                                        <input type="hidden" name="dni" value="<c:out value='${n.dni}'/>">
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