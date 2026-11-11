
package org.example.util;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {
        try (Connection conexion = ConexionBD.conectar()) {
            System.out.println("Conexión a MariaDB correcta");
            System.out.println("Base de datos: " +
                    conexion.getCatalog());
        } catch (SQLException e) {
            System.out.println("Error al conectar con MariaDB");
            e.printStackTrace();
        }
    }
}
