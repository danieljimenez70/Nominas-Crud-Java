package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mariadb://localhost:3306/nominas";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";


    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encuentra el controlador JDBC de MariaDB", e);
        }

        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}