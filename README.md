# Nominas-Crud-Java (Unidad 3: MVC)

Aplicación web de gestión de empleados y nóminas, refactorizada a MVC con Front Controller.

## Requisitos
- Java 17 o superior
- Maven
- MariaDB

## Puesta en marcha
1. Crear la base de datos `nominas` en MariaDB con las tablas `empleados` y `nominas`.
2. Copiar `src/main/resources/db.properties.example` a `src/main/resources/db.properties` y poner la contraseña propia.
3. Arrancar con Tomcat 9 integrado (plugin Cargo):
   ```
   mvn clean package cargo:run
   ```
4. Abrir http://localhost:8080/Nominas-Crud-Java/

## Estructura
- `controller`: Front Controller (único servlet, `/app/*`)
- `command`: Command y CommandFactory
- `service`: capa de servicio (reglas de negocio)
- `dao` / `model`: acceso a datos y entidades
- `util`: ConexionBD (Singleton)
- `WEB-INF/views`: vistas JSP con JSTL