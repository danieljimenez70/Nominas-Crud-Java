package org.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Patrón Singleton: una única instancia que guarda la configuración
 * de la base de datos y entrega las conexiones.
 * La configuración se lee una sola vez desde db.properties.
 */
public final class ConexionBD {

    private final String url;
    private final String usuario;
    private final String password;

    // Constructor privado: nadie puede hacer "new ConexionBD()"
    private ConexionBD() {
        Properties propiedades = new Properties();

        try (InputStream in = ConexionBD.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (in == null) {
                throw new IllegalStateException("No se encuentra el archivo db.properties");
            }
            propiedades.load(in);

        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer db.properties", e);
        }

        this.url = propiedades.getProperty("db.url");
        this.usuario = propiedades.getProperty("db.usuario");
        this.password = propiedades.getProperty("db.password");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("No se encuentra el controlador JDBC de MariaDB", e);
        }
    }

    // La instancia se crea la primera vez que se pide (segura entre hilos)
    private static class Holder {
        private static final ConexionBD INSTANCIA = new ConexionBD();
    }

    public static ConexionBD getInstance() {
        return Holder.INSTANCIA;
    }

    public Connection getConexion() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }
}