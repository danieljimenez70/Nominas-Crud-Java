package org.example.dao;

import org.example.model.Empleado;
import org.example.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public List<Empleado> listar() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados";

        try (Connection conexion = ConexionBD.getInstance().getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("sexo"),
                        rs.getInt("categoria"),
                        rs.getInt("anyos")
                );

                empleados.add(empleado);
            }
        }

        return empleados;
    }

    public Empleado buscarPorDni(String dni) throws SQLException {
        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE dni = ?";

        try (Connection conexion = ConexionBD.getInstance().getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("sexo"),
                            rs.getInt("categoria"),
                            rs.getInt("anyos")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.getInstance().getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, empleado.getDni());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getSexo());
            ps.setInt(4, empleado.getCategoria());
            ps.setInt(5, empleado.getAnyos());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Empleado empleado) throws SQLException {
        String sql = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";

        try (Connection conexion = ConexionBD.getInstance().getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getSexo());
            ps.setInt(3, empleado.getCategoria());
            ps.setInt(4, empleado.getAnyos());
            ps.setString(5, empleado.getDni());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM empleados WHERE dni = ?";

        try (Connection conexion = ConexionBD.getInstance().getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dni);

            return ps.executeUpdate() > 0;
        }
    }
}